package com.example.petProject.changeRequestFeature.model.dto;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.petProject.changeRequestFeature.model.enumTypes.OperationType;
import com.example.petProject.model.enumTypes.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestDTO {
    private Long id;

    private String createdBy;

    private UserRole userRole;

    private ChangeRequestState changeRequestState;

    private OperationType operationType;

    private String currentObjectState;

    private String newObjectState;

    private Set<ChangeRequestCommentEntity> changeRequestCommentEntities;
}
