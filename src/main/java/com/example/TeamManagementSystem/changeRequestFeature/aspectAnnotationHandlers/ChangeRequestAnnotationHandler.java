package com.example.TeamManagementSystem.changeRequestFeature.aspectAnnotationHandlers;

import com.example.TeamManagementSystem.changeRequestFeature.annotation.Approver;
import com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.OperationType;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.exception.RequestApproval;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Class.forName;

@Aspect
@Component
public class ChangeRequestAnnotationHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChangeRequestRepository changeRequestRepository;
    @Autowired
    private Sources<Long> sources;

    @Pointcut("@annotation(com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest)")
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
        ChangeRequestEntity changeRequestObject = createChangeRequestObject(joinPoint, userDetails, changeRequestAnnotation, approver);
        changeRequestOperation(joinPoint, changeRequestAnnotation, approver, changeRequestObject);
        changeRequestRepository.save(changeRequestObject);
    }


    private ChangeRequestEntity createChangeRequestObject(JoinPoint joinPoint, AppUserDetails userDetails, ChangeRequest changeRequest, Approver annotation) {
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
                readOperation(changeRequestEntity);
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


    private void readOperation(ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        changeRequestEntity.setOperationType(OperationType.READ);
    }


    private String getFunctionQualifier(Class<? extends ChangeRequestEntityMarker> domainClass, String functionName) {
        return (functionName + "(" + domainClass.getName() + ")").replace(" /.,-_=\"\\", "");
    }
}
