package com.example.TeamManagementSystem.changeRequestFeature.events.dto;

import com.example.TeamManagementSystem.changeRequestFeature.domain.dto.ChangeRequestDTO;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeRequestEventDetails {
    private String message;
    private OperationType operationType;
    private ChangeRequestDTO changeRequestDTO;
}
