package com.example.petProject.changeRequestFeature.aspect;

import com.example.petProject.changeRequestFeature.annotation.Approver;
import com.example.petProject.changeRequestFeature.annotation.ChangeRequest;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.model.entityMarker.ChangeRequestEntityMarker;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.petProject.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.exception.RequestApproval;
import com.example.petProject.model.enumTypes.auth.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class ChangeRequestAnnotation {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ChangeRequestRepository changeRequestRepository;

    @Pointcut("@annotation(com.example.petProject.changeRequestFeature.annotation.ChangeRequest)")
    public void annotationPointCut() {
    }


    //TODO create approving of change request, updating and writing comments to one

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Before(value = "annotationPointCut()", argNames = "joinPoint")
    public Object executeAnnotation(JoinPoint joinPoint) throws Throwable {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getUserRole() == UserRole.ADMIN) {
            return joinPoint;
        }
        Class<?> clazz = joinPoint.getTarget().getClass();

        ChangeRequest changeRequest = clazz.getMethod(joinPoint.getSignature().getName(), Long.class).getAnnotation(ChangeRequest.class);

        Approver annotation = clazz.getAnnotation(Approver.class);

        Method method = annotation.repository().getMethod("getById", Long.class);


        ChangeRequestEntity changeRequestEntity = new ChangeRequestEntity();
        changeRequestEntity.setChangeRequestState(ChangeRequestState.PENDING);
        changeRequestEntity.setCreatedBy(userDetails.getUsername());
        changeRequestEntity.setCreatedAt(new Date());
        changeRequestEntity.setUserRole(annotation.userRole());
        changeRequestEntity.setOperationType(changeRequest.operationType());
        changeRequestOperation(joinPoint, changeRequest, annotation, method, changeRequestEntity);
        changeRequestRepository.save(changeRequestEntity);

        throw new RequestApproval("You do not have permission to change objects here, changes approval are requested");
    }

    private void changeRequestOperation(JoinPoint joinPoint,
                                        ChangeRequest changeRequest,
                                        Approver annotation,
                                        Method method,
                                        ChangeRequestEntity changeRequestEntity) throws JsonProcessingException, IllegalAccessException, InvocationTargetException {
        switch (changeRequest.operationType()) {
            case CREATE: {
                ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
                changeRequestEntity.setNewObjectState(changeableObject);
                break;
            }
            case DELETE: {
                Long changeableObject = (Long) joinPoint.getArgs()[0];
                ChangeRequestEntityMarker currentObjectState = (ChangeRequestEntityMarker) method.invoke(applicationContext.getBean(annotation.repository()), (Long) changeableObject);
                //changeRequestEntity.setCurrentObjectState(currentObjectState);
                break;
            }
            default: {
                ChangeRequestEntityMarker changeableObject = (ChangeRequestEntityMarker) joinPoint.getArgs()[0];
                ChangeRequestEntityMarker currentObjectState = (ChangeRequestEntityMarker) method.invoke(applicationContext.getBean(annotation.repository()), (Long) changeableObject.getId());
                changeRequestEntity.setNewObjectState(changeableObject);
                changeRequestEntity.setCurrentObjectState(currentObjectState);
                break;
            }
        }
    }
}
