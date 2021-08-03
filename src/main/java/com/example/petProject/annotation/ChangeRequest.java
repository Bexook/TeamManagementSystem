package com.example.petProject.annotation;

import com.example.petProject.model.enumTypes.auth.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeRequest {

    UserRole[] userRole() default UserRole.ADMIN;


}
