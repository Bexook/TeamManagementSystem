package com.example.petProject.aspect.changeRequestFeature;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ChangeRequestAnnotation {


    @Pointcut("@annotation(com.example.petProject.annotation.ChangeRequest)")
    public void annotationPointCut(){}


    @Before(value = "annotationPointCut()", argNames = "joinPoint")
    public void executeAnnotation(JoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getTarget());
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }



}
