package com.example.TeamManagementSystem.service.model.impl;


import com.example.TeamManagementSystem.repository.TimeLogRepository;
import com.example.TeamManagementSystem.service.model.TimeLogService;
import com.tms.common.domain.dto.TimeLogDTO;
import com.tms.common.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeLogServiceImpl implements TimeLogService {

    @Autowired
    private TimeLogRepository timeLogRepository;

    @Override
    public List<TimeLogDTO> findByUserAndSpan(UserDTO userDTO, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<TimeLogDTO> findBySpan(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<TimeLogDTO> findByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void saveTimeLog(TimeLogDTO timeLogDTO) {

    }

    @Override
    public void update(TimeLogDTO timeLogDTO) {

    }

    @Override
    public void delete(TimeLogDTO timeLogDTO) {

    }
}
