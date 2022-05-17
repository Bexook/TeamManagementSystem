package com.example.TeamManagementSystem.changeRequestFeature.service;

import com.tms.common.changeRequestDomain.dto.ChangeRequestReviewDTO;
import com.tms.common.changeRequestDomain.entity.ChangeRequestCommentEntity;
import com.tms.common.changeRequestDomain.enumTypes.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChangeRequestCommentService {

    void addComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    void removeComment(ChangeRequestReviewDTO changeRequestReviewDTO);

    Page<ChangeRequestCommentEntity> getAllCommentCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable);

    List<ChangeRequestCommentEntity> addAllComments(List<ChangeRequestCommentEntity> cr);

}
