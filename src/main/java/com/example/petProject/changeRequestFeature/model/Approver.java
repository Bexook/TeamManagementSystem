package com.example.petProject.changeRequestFeature.model;

import com.example.petProject.model.enumTypes.auth.UserRole;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

@Data
public class Approver {

    private Long id;
    private UserRole userRole;
    private Class<?> jpaRepository;

}
