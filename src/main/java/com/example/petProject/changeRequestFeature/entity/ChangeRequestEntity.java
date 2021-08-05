package com.example.petProject.changeRequestFeature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "change_request")
public class ChangeRequestEntity {


    @Id
    @Column(name = "id")
    private Long id;



}
