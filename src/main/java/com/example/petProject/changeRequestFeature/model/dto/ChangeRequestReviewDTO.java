package com.example.petProject.changeRequestFeature.model.dto;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestState;
import lombok.Data;

import java.util.List;

@Data
public class ChangeRequestReviewDTO {

    private Long changeRequestId;
    private List<ChangeRequestCommentEntity> comment;
    private Long approverId;
    private ChangeRequestState currentState;

}
