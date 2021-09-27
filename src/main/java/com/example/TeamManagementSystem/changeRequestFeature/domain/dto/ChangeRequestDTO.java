package com.example.TeamManagementSystem.changeRequestFeature.domain.dto;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestCommentEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.OperationType;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestDTO {
    private Long id;
    private String createdBy;
    private String modifiedBy;
    private UserRole userRole;
    private ChangeRequestState changeRequestState;
    private OperationType operationType;
    private String currentObjectState;
    private String newObjectState;
    private String domainClass;
    private Set<ChangeRequestCommentEntity> changeRequestCommentEntities;
    private boolean relevant = true;
    private String objectRepo;
}
