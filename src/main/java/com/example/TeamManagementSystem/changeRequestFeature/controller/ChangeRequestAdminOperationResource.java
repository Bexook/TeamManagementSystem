package com.example.TeamManagementSystem.changeRequestFeature.controller;

import com.example.TeamManagementSystem.changeRequestFeature.domain.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestCommentEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.events.publishers.ChangeRequestEventPublisher;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestService;
import com.example.TeamManagementSystem.domain.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-request/approver")
public class ChangeRequestAdminOperationResource {


    @Autowired
    private ChangeRequestService changeRequestService;
    @Autowired
    private ChangeRequestCommentService changeRequestCommentService;

    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String DEFAULT_PAGE_NUMBER = "0";


    @PostMapping("/update")
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE')")
    public ResponseEntity<ChangeRequestReviewDTO> review(@RequestBody ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException {
        return ResponseEntity.ok(changeRequestService.update(changeRequestReviewDTO));
    }


    @PostMapping("/comment")
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE','USER')")
    public ResponseEntity<List<ChangeRequestCommentEntity>> commentChangeRequest(@RequestBody ChangeRequestReviewDTO changeRequestReviewDTO) {
        return ResponseEntity.accepted().body(changeRequestCommentService.addAllComments(changeRequestReviewDTO.getComment()));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE')")
    public ResponseEntity<ChangeRequestEntity> getChangeRequest(@PathVariable("id") Long id) {
        return ResponseEntity.ok(changeRequestService.getById(id));
    }


    @GetMapping("/get-list")
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE')")
    public ResponseEntity<Page<ChangeRequestEntity>> getAllChangeRequests(@RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false, name = "size") Integer size,
                                                                          @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER, required = false, name = "page") Integer page) {
        return ResponseEntity.ok().body(changeRequestService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/get-by-criteria")
    @PreAuthorize(("@userAccessValidation.hasAuthority('APPROVE')"))
    public ResponseEntity<Page<ChangeRequestEntity>> getAlChangeRequestsByCriteria(@RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false, name = "size") Integer size,
                                                                                   @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER, required = false, name = "page") Integer page,
                                                                                   @RequestBody SearchStrategy searchStrategy) {
        return ResponseEntity.ok(changeRequestService.getByCriteria(searchStrategy.getSearchCriteria(), searchStrategy.getValue(), PageRequest.of(page, size, Sort.Direction.DESC)));
    }


}
