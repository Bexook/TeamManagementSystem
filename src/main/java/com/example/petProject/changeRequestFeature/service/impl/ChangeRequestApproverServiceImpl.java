package com.example.petProject.changeRequestFeature.service.impl;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.petProject.changeRequestFeature.service.ChangeRequestApproverService;
import com.example.petProject.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.petProject.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeRequestApproverServiceImpl implements ChangeRequestApproverService {

    @Autowired
    private ChangeRequestService changeRequestService;
    @Autowired
    private ChangeRequestCommentService changeRequestCommentService;


    @Override
    public void approveChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4) {

    }

    @Override
    public void deleteChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4) {

    }

    @Override
    public ChangeRequestReviewDTO updateChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4) {

    }
}
