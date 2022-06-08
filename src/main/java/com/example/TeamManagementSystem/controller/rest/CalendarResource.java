package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.service.model.TimeLogService;
import com.tms.common.domain.dto.TimeLogDTO;
import com.tms.common.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarResource {

    @Autowired
    private TimeLogService timeLogService;

    @PostMapping("/create")
    public void createdTimeLog(@RequestBody final TimeLogDTO timeLog) {
        timeLogService.saveTimeLog(timeLog);
    }

    @GetMapping("/logs/get")
    public ResponseEntity<List<TimeLogDTO>> getByUserAndTimeSpan(@RequestParam("startDate")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate start,
                                                                 @RequestParam("endDate")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)final LocalDate end) {
        return ResponseEntity.ok().body(timeLogService.findByUserAndSpan(AuthorizationUtils.getCurrentUsername(), start, end));
    }

    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    @GetMapping("/manager/get")
    public ResponseEntity<List<TimeLogDTO>> getByUserEmailAndTimeSpan(final String userEmail, final LocalDate start, final LocalDate end) {
        return ResponseEntity.ok().body(timeLogService.findByUserAndSpan(userEmail, start, end));
    }

    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    @GetMapping("/manager/get/all")
    public ResponseEntity<List<TimeLogDTO>> getAllTeamMembersTimeLogsForManager(@RequestParam(value = "teamId", required = false) final Long teamId) {
        return ResponseEntity.ok().body(timeLogService.findByTeam(teamId));
    }


}