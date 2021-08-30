package com.example.petProject.changeRequestFeature.service.impl;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.petProject.changeRequestFeature.repository.ChangeRequestCommentRepository;
import com.example.petProject.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.petProject.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChangeRequestCommentServiceImpl implements ChangeRequestCommentService {

    @Autowired
    private ChangeRequestService changeRequestService;
    @Autowired
    private ChangeRequestCommentRepository changeRequestCommentRepository;

    @Override
    @Transactional

    public void addComment(ChangeRequestReviewDTO changeRequestReviewDTO) {

    }

    @Override
    @Transactional
    public void removeComment(ChangeRequestReviewDTO changeRequestReviewDTO) {

    }

    @Override
    public List<ChangeRequestCommentEntity> getAllCommentByChangeRequestId(Long changeRequestId) {
        return null;
    }

}
