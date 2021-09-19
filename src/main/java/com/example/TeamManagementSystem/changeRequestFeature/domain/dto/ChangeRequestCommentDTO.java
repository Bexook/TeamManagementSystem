package com.example.TeamManagementSystem.changeRequestFeature.domain.dto;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestCommentDTO {

    private Long id;

    private String comment;

    private String commentBy;

    private ChangeRequestEntity changeRequestEntity;

}
