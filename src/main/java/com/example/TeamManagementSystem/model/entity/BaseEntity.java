package com.example.TeamManagementSystem.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;

}
