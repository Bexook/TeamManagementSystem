package com.example.TeamManagementSystem.changeRequestFeature.service;

import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChangeRequestCommentService {

    void addComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    void removeComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    Page<ChangeRequestCommentEntity> getAllCommentCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable);

    List<ChangeRequestCommentEntity> addAllComments(List<ChangeRequestCommentEntity> cr);

}