package com.example.TeamManagementSystem.changeRequestFeature.aspectAnnotationHandlers;

import com.tms.common.annotation.Approver;
import com.tms.common.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.changeRequestFeature.events.ChangeRequestEvent;
import com.example.TeamManagementSystem.changeRequestFeature.events.dto.ChangeRequestEventDetails;
import com.example.TeamManagementSystem.changeRequestFeature.events.publishers.ChangeRequestEventPublisher;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestRepository;

import com.example.TeamManagementSystem.exception.RequestApproval;
import com.example.TeamManagementSystem.service.UserMessagesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.common.changeRequestDomain.dto.ChangeRequestDTO;
import com.tms.common.changeRequestDomain.entity.ChangeRequestEntity;
import com.tms.common.changeRequestDomain.entityMarker.ChangeRequestEntityMarker;
import com.tms.common.changeRequestDomain.enumTypes.ChangeRequestState;
import com.tms.common.changeRequestDomain.enumTypes.OperationType;
import com.tms.common.domain.enumTypes.auth.UserRole;
import com.tms.common.userAuthDataConfiguration.AppUserDetails;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import javax.transaction.Transactional;
import java.time.Clock;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class ChangeRequestAnnotationHandler {

    @Autowired
    private ChangeRequestEventPublisher changeRequestEventPublisher;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChangeRequestRepository changeRequestRepository;
    @Autowired
    private Sources<Long> sources;
    @Autowired
    private UserMessagesService userMessagesService;

    @Pointcut("@annotation(com.tms.common.annotation.ChangeRequest)")
    public void annotationPointCut() {
    }


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Before(value = "annotationPointCut()", argNames = "joinPoint")
    public Object executeAnnotation(JoinPoint joinPoint) throws Throwable {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getUserRole() == UserRole.valueOf(sources.getApproverRole())) {
            return joinPoint;
        }
        Class<?> clazz = joinPoint.getTarget().getClass();
        Class<?>[] args = new Class<?>[joinPoint.getArgs().length];
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            args[i] = joinPoint.getArgs()[i].getClass();
        }
        ChangeRequest changeRequestAnnotation = clazz.getDeclaredMethod(joinPoint.getSignature().getName(), args).getAnnotation(ChangeRequest.class);

        Approver annotation = clazz.getAnnotation(Approver.class);
        if (Objects.isNull(annotation)) {
            throw new SystemException("Need to mark service class with @Approver()");
        }

        proceedChangeRequest(joinPoint, userDetails, changeRequestAnnotation, annotation);
        throw new RequestApproval("You do not have permission to change objects here, changes approval are requested");
    }


    private void proceedChangeRequest(JoinPoint joinPoint, AppUserDetails userDetails, ChangeRequest changeRequestAnnotation, Approver approver) throws JsonProcessingException, ClassNotFoundException {
        ChangeRequestEntity changeRequestObject = createChangeRequestObject(userDetails, changeRequestAnnotation, approver);
        changeRequestOperation(joinPoint, changeRequestAnnotation, approver, changeRequestObject);
        changeRequestRepository.save(changeRequestObject);
    }


    private ChangeRequestEntity createChangeRequestObject(AppUserDetails userDetails, ChangeRequest changeRequest, Approver annotation) {
        ChangeRequestEntity changeRequestEntity = new ChangeRequestEntity();
        changeRequestEntity.setChangeRequestState(ChangeRequestState.PENDING);
        changeRequestEntity.setCreatedBy(userDetails.getUsername());
        changeRequestEntity.setUserRole(UserRole.valueOf(sources.getApproverRole()));
        changeRequestEntity.setOperationType(changeRequest.operationType());
        changeRequestEntity.setRelevant(true);
        changeRequestEntity.setDomainClass(annotation.domainClass().getName());
        changeRequestEntity.setObjectRepo(annotation.repository().getName());

        return changeRequestEntity;
    }

    private void changeRequestOperation(JoinPoint joinPoint,
                                        ChangeRequest changeRequest,
                                        Approver approver,
                                        ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        switch (changeRequest.operationType()) {
            case CREATE: {
                createOperation(joinPoint, changeRequestEntity, approver.domainClass());
                break;
            }
            case DELETE: {
                deleteOperation(joinPoint, changeRequestEntity, approver.domainClass());
                break;
            }
            case UPDATE: {
                updateOperation(joinPoint, changeRequestEntity, approver.domainClass());
                break;
            }
            case READ: {
                if (Objects.isNull(joinPoint.getArgs())) {
                    List<?> currentObjectState = sources.loadRepository(changeRequestEntity.getDomainClass().getClass()).findAll();
                    changeRequestEntity.setCurrentObjectState(objectMapper.writeValueAsString(currentObjectState));
                    readOperation(changeRequestEntity);
                }
            }
            default: {
                throw new IllegalArgumentException("Change request operation does not exist");
            }
        }
    }

    private void createOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity, Class<? extends ChangeRequestEntityMarker> domainClass) throws JsonProcessingException {
        ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
        changeRequestEntity.setNewObjectState(objectMapper.writeValueAsString(changeableObject));
        changeRequestEntity.setOperationType(OperationType.CREATE);
    }

    private void updateOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity, Class<? extends ChangeRequestEntityMarker> domainClass) throws JsonProcessingException {
        ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
        ChangeRequestEntityMarker currentObjectState = sources.executeRepositoryFunction(domainClass, (repo) -> repo.getById(changeableObject.getId()));
        changeRequestEntity.setNewObjectState(objectMapper.writeValueAsString(changeableObject));
        changeRequestEntity.setCurrentObjectState(objectMapper.writeValueAsString(currentObjectState));
        changeRequestEntity.setOperationType(OperationType.UPDATE);
    }

    private void deleteOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity, Class<? extends ChangeRequestEntityMarker> domainClass) throws JsonProcessingException {
        Long changeableObject = (Long) joinPoint.getArgs()[0];
        List<ChangeRequestEntityMarker> listOfEntities = (List<ChangeRequestEntityMarker>) sources.loadRepository(domainClass).findAll();
        ChangeRequestEntityMarker currentObjectState = listOfEntities.stream().filter(e -> e.getId().equals(changeableObject)).findFirst().orElseThrow();
        changeRequestEntity.setCurrentObjectState(objectMapper.writeValueAsString(currentObjectState));
        changeRequestEntity.setOperationType(OperationType.DELETE);
    }


    private void readOperation(ChangeRequestEntity changeRequestEntity) {
        changeRequestEntity.setOperationType(OperationType.READ);
//        userMessagesService.sendMessage();
    }


    private String getFunctionQualifier(Class<? extends ChangeRequestEntityMarker> domainClass, String functionName) {
        return (functionName + "(" + domainClass.getName() + ")").replace(" /.,-_=\"\\", "");
    }


    private void publishChangeRequestEvent(final ChangeRequestDTO changeRequest) {
        final ChangeRequestEvent changeRequestEvent = new ChangeRequestEvent(changeRequest, Clock.systemUTC(), new ChangeRequestEventDetails("Change request created", changeRequest.getOperationType(), changeRequest));
        changeRequestEventPublisher.publishEvent(changeRequestEvent);
    }
}
