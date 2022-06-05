package com.example.TeamManagementSystem.service.calander;

import com.example.TeamManagementSystem.domain.dto.TimeLogDTO;
import com.example.TeamManagementSystem.domain.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface TimeLogService {

    List<TimeLogDTO> findByUserAndSpan(final String userEmail, final LocalDate start, final LocalDate end);

    List<TimeLogDTO> findBySpan(final LocalDate start, final LocalDate end);

    List<TimeLogDTO> findByUser(final UserDTO userDTO);

    List<TimeLogDTO> findByTeam(final Long teamId);

    void saveTimeLog(TimeLogDTO timeLogDTO);

    void update(TimeLogDTO timeLogDTO);

    void delete(TimeLogDTO timeLogDTO);

}
