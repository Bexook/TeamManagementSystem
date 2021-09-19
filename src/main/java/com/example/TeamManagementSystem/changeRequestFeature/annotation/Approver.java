package com.example.TeamManagementSystem.changeRequestFeature.annotation;

import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Approver {

    UserRole userRole() default UserRole.ADMIN;

    Class<?> repository();
}
