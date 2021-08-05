package com.example.petProject.changeRequestFeature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class ChangeRequestComment {

    @Id
    @Column(name = "id")
    private Long id;

}
