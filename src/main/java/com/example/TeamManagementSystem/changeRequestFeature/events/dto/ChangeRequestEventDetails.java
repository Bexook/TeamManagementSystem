package com.example.TeamManagementSystem.changeRequestFeature.events.dto;


import com.tms.dao.tmsdao.changeRequestDomain.dto.ChangeRequestDTO;
import com.tms.dao.tmsdao.changeRequestDomain.enumTypes.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeRequestEventDetails {
    private String message;
    private OperationType operationType;
    private ChangeRequestDTO changeRequestDTO;
}
