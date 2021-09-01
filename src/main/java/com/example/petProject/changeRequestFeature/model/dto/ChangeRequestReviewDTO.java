package com.example.petProject.changeRequestFeature.model.dto;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestState;
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
