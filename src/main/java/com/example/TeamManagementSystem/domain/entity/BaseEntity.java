package com.example.TeamManagementSystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Transactional
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    @PrePersist
    public void initPersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void initUpdate() {
        modifiedAt = LocalDateTime.now();
    }


}
