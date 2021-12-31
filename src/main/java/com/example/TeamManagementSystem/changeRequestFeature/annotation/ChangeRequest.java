package com.example.TeamManagementSystem.changeRequestFeature.annotation;


import com.tms.dao.tmsdao.changeRequestDomain.enumTypes.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeRequest {
    OperationType operationType();
}
