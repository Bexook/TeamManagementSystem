package com.example.petProject.changeRequestFeature.aspect;

import com.example.petProject.changeRequestFeature.model.Approver;
import com.example.petProject.changeRequestFeature.repository.ChangeRequestRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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


    @Before(value = "annotationPointCut()", argNames = "joinPoint")
    public Object executeAnnotation(JoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        com.example.petProject.changeRequestFeature.annotation.Approver annotation = clazz.getAnnotation(com.example.petProject.changeRequestFeature.annotation.Approver.class);
        Approver approver = new Approver();

        approver.setId(annotation.id());
        approver.setUserRole(annotation.userRole());
        approver.setJpaRepository(annotation.repository());

        Method method = approver.getJpaRepository().getMethod("getById", Long.class);

        throw new RuntimeException();
    }
}
