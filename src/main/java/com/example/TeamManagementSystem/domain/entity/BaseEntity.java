package com.example.TeamManagementSystem.domain.entity;

import lombok.Data;

import javax.persistence.*;
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
