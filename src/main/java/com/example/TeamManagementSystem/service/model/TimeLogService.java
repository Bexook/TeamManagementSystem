package com.example.TeamManagementSystem.service.model;


import com.tms.common.domain.dto.TimeLogDTO;
import com.tms.common.domain.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface TimeLogService {

    List<TimeLogDTO> findByUserAndSpan(final String userEmail, final LocalDate start, final LocalDate end);

    List<TimeLogDTO> findBySpan(final LocalDate start, final LocalDate end);

    List<TimeLogDTO> findByTeam(final Long teamId);

    List<TimeLogDTO> findByUser(final UserDTO userDTO);

    void saveTimeLog(TimeLogDTO timeLogDTO);

    void update(TimeLogDTO timeLogDTO);

    void delete(TimeLogDTO timeLogDTO);

}
