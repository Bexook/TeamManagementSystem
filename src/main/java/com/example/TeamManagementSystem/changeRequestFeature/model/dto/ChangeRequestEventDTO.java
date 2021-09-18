package com.example.TeamManagementSystem.changeRequestFeature.model.dto;

import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestEventDTO {

    private String message;
    private ChangeRequestEventType changeRequestEventType;
    private ChangeRequestEntity changeRequestEntity;
    private OperationType operationType;

}
