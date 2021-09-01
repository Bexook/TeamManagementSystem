package com.example.petProject.changeRequestFeature.service;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;

public interface ChangeRequestApproverService {


    void approveChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4);

    void deleteChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4);

    ChangeRequestReviewDTO updateChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO4);

}
