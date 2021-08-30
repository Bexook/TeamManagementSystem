package com.example.petProject.changeRequestFeature.service;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;

import java.util.List;

public interface ChangeRequestCommentService {

    void addComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    void removeComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    List<ChangeRequestCommentEntity> getAllCommentByChangeRequestId(Long changeRequestId);


}
