package com.example.TeamManagementSystem.model.entity;

import com.example.TeamManagementSystem.model.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.changeRequestFeature.model.entityMarker.ChangeRequestEntityMarker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


@Data
@Entity
@Table(name = "app_user")
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE app_user SET is_active = 0 WHERE public.app_user.id= ? ", check = ResultCheckStyle.COUNT)
@FilterDef(name = "activeFilter", parameters = @ParamDef(name = "isActive", type = "boolean"))
@Filter(name = "activeFilter", condition = "is_active=:isActive")
public class UserEntity extends BaseEntity implements ChangeRequestEntityMarker<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_enable")
    private boolean isEnable;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "is_credentials_expired")
    private boolean isCredentialsExpired;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;


}
