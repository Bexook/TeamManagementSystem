package com.example.TeamManagementSystem.changeRequestFeature.annotation;



import com.tms.dao.tmsdao.changeRequestDomain.entityMarker.ChangeRequestEntityMarker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Approver {

    Class<?> repository();

    Class<? extends ChangeRequestEntityMarker> domainClass();
}
