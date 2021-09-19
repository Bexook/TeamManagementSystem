package com.example.TeamManagementSystem.changeRequestFeature.aspect;

import com.example.TeamManagementSystem.changeRequestFeature.annotation.Approver;
import com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.eventPublisher.ChangeRequestEventPublisher;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestEventDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.TeamManagementSystem.exception.RequestApproval;
import com.example.TeamManagementSystem.changeRequestFeature.model.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
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

@Aspect
@Component
public class ChangeRequestAnnotationHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChangeRequestRepository changeRequestRepository;
    @Autowired
    private ChangeRequestEventPublisher changeRequestEventPublisher;
    @Autowired
    private Sources<Long> sources;

    @Pointcut("@annotation(com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest)")
    public void annotationPointCut() {
    }


    //TODO create approving of change request, updating and writing comments to one

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Before(value = "annotationPointCut()", argNames = "joinPoint")
    public Object executeAnnotation(JoinPoint joinPoint) throws Throwable {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userDetails.getUserRole() == UserRole.ADMIN) {
//            return joinPoint;
//        }
        Class<?> clazz = joinPoint.getTarget().getClass();
        ChangeRequest changeRequestAnnotation = clazz.getMethod(joinPoint.getSignature().getName(), Long.class).getAnnotation(ChangeRequest.class);

        Approver annotation = clazz.getAnnotation(Approver.class);
        if (Objects.isNull(annotation)) {
            throw new SystemException("Need to mark service class with @Approver()");
        }

        proceedChangeRequest(joinPoint, userDetails, changeRequestAnnotation, annotation);
        throw new RequestApproval("You do not have permission to change objects here, changes approval are requested");
    }


    private void proceedChangeRequest(JoinPoint joinPoint, AppUserDetails userDetails, ChangeRequest changeRequestAnnotation, Approver annotation) throws JsonProcessingException, ClassNotFoundException {
        ChangeRequestEntity changeRequestObject = createChangeRequestObject(joinPoint, userDetails, changeRequestAnnotation, annotation);
        changeRequestOperation(joinPoint, changeRequestAnnotation, changeRequestObject);
        changeRequestRepository.save(changeRequestObject);
        changeRequestEventPublisher.publishChangeRequestEvent(this, createChangeRequestEventDTO(changeRequestObject));
    }

    private ChangeRequestEventDTO createChangeRequestEventDTO(ChangeRequestEntity changeRequestEntity) {
        return new ChangeRequestEventDTO("ChangeRRequest is PENDING APPROVAL", ChangeRequestEventType.CREATE, changeRequestEntity, changeRequestEntity.getOperationType());
    }

    private ChangeRequestEntity createChangeRequestObject(JoinPoint joinPoint, AppUserDetails userDetails, ChangeRequest changeRequest, Approver annotation) {
        ChangeRequestEntity changeRequestEntity = new ChangeRequestEntity();
        changeRequestEntity.setChangeRequestState(ChangeRequestState.PENDING);
        changeRequestEntity.setCreatedBy(userDetails.getUsername());
        changeRequestEntity.setUserRole(annotation.userRole());
        changeRequestEntity.setOperationType(changeRequest.operationType());
        changeRequestEntity.setRelevant(true);
        changeRequestEntity.setObjectType(joinPoint.getTarget().getClass().getName());
        changeRequestEntity.setObjectRepo(annotation.repository().getName());
        return changeRequestEntity;
    }


    private void changeRequestOperation(JoinPoint joinPoint,
                                        ChangeRequest changeRequest,
                                        ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        switch (changeRequest.operationType()) {
            case CREATE: {
                createOperation(joinPoint, changeRequestEntity);
                break;
            }
            case DELETE: {
                deleteOperation(joinPoint, changeRequestEntity);
                break;
            }
            case UPDATE: {
                updateOperation(joinPoint, changeRequestEntity);
                break;
            }
            default: {
                throw new IllegalArgumentException("Change request operation does not exist");
            }
        }
    }

    private void createOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
        changeRequestEntity.setNewObjectState(objectMapper.writeValueAsString(changeableObject));
        changeRequestEntity.setOperationType(OperationType.CREATE);
    }

    private void updateOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
        ChangeRequestEntityMarker currentObjectState = sources.executePersistFunction(UserEntity.class, (repo) -> repo.getById(changeableObject.getId()));
        changeRequestEntity.setNewObjectState(objectMapper.writeValueAsString(changeableObject));
        changeRequestEntity.setCurrentObjectState(objectMapper.writeValueAsString(currentObjectState));
        changeRequestEntity.setOperationType(OperationType.UPDATE);
    }

    private void deleteOperation(JoinPoint joinPoint, ChangeRequestEntity changeRequestEntity) throws JsonProcessingException {
        Long changeableObject = (Long) joinPoint.getArgs()[0];
        List<ChangeRequestEntityMarker> listOfEntities = (List<ChangeRequestEntityMarker>) sources.findCollectionOfElements("findAll(UserEntity)", List.class);
        ChangeRequestEntityMarker currentObjectState = listOfEntities.stream().filter(e -> e.getId().equals(changeableObject)).findFirst().orElseThrow();
        changeRequestEntity.setCurrentObjectState(objectMapper.writeValueAsString(currentObjectState));
        changeRequestEntity.setOperationType(OperationType.DELETE);
    }
}
