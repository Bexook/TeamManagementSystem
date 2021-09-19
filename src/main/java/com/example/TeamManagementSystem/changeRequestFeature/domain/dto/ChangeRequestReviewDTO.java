package com.example.TeamManagementSystem.changeRequestFeature.domain.dto;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestCommentEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.ChangeRequestState;
import lombok.Data;

import java.util.List;

@Data
public class ChangeRequestReviewDTO {

    private Long approverId;
    private Long createdBy;
    private Long modifiedBy;
    private ChangeRequestEntity changeRequestEntity;
    private List<ChangeRequestCommentEntity> comment;
    private ChangeRequestState currentState;

}
