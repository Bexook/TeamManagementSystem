package com.example.TeamManagementSystem.domain.entity;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.domain.enumTypes.auth.AccessType;
import com.example.TeamManagementSystem.domain.enumTypes.auth.Authority;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "app_user")
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE app_user SET is_active = 0 WHERE public.app_user.id= ? ", check = ResultCheckStyle.COUNT)
@FilterDef(name = "activeFilter", parameters = @ParamDef(name = "isActive", type = "boolean"))
@Filter(name = "activeFilter", condition = "is_active=:isActive")
public class UserEntity extends BaseEntity implements ChangeRequestEntityMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "access_type")
    @Enumerated(value = EnumType.STRING)
    private AccessType accessType;

    @Column(name = "authoritis")
    @ElementCollection(targetClass = Authority.class)
    @Enumerated(value = EnumType.STRING)
    private Set<Authority> authority;

    @Column(name = "is_credentials_expired")
    private boolean isCredentialsExpired;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Fetch(value = FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_time_log",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "time_log_id", referencedColumnName = "id")
            }

    )
    private List<TimeLogEntity> timeLog;

}
