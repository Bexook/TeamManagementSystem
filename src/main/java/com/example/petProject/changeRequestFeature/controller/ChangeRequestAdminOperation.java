package com.example.petProject.changeRequestFeature.controller;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.petProject.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.petProject.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/change-request/approver")
public class ChangeRequestAdminOperation {


    @Autowired
    private ChangeRequestService changeRequestService;
    @Autowired
    private ChangeRequestCommentService changeRequestCommentService;

//
//    @PostMapping("/update-state/{change-request-id}")
//    public ResponseEntity<ChangeRequestReviewDTO> review(@RequestBody ChangeRequestReviewDTO changeRequestReviewDTO,
//                                                         @PathVariable("change-request-id") Long id) {
//        return ResponseEntity <>
//    }
}
