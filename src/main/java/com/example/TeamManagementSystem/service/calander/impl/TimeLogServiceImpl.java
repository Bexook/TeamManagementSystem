package com.example.TeamManagementSystem.service.calander.impl;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.TimeLogDTO;
import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.entity.TimeLogEntity;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.TimeLogRepository;
import com.example.TeamManagementSystem.service.team.TeamMemberService;
import com.example.TeamManagementSystem.service.calander.TimeLogService;
import com.example.TeamManagementSystem.service.user.UserService;
import com.example.TeamManagementSystem.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TimeLogServiceImpl implements TimeLogService {


    @Autowired
    private UserService userService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private TimeLogRepository timeLogRepository;
    @Autowired
    private OrikaBeanMapper mapper;

    @Override
    public List<TimeLogDTO> findByUserAndSpan(String userEmail, LocalDate start, LocalDate end) {
        return mapper.mapAsList(timeLogRepository.findByCriteria(AuthorizationUtils.getCurrentUsername(), start, end), TimeLogDTO.class);
    }

    @Override
    public List<TimeLogDTO> findBySpan(LocalDate start, LocalDate end) {
        return mapper.mapAsList(timeLogRepository.findByTimeSpan(start, end), TimeLogDTO.class);
    }

    @Override
    public List<TimeLogDTO> findByUser(UserDTO userDTO) {
        return mapper.mapAsList(timeLogRepository.findByCreatedBy(userDTO.getEmail()), TimeLogDTO.class);
    }

    @Override
    public void saveTimeLog(TimeLogDTO timeLogDTO) {
        timeLogRepository.save(mapper.map(timeLogDTO, TimeLogEntity.class));
    }

    @Override
    public void update(TimeLogDTO timeLogDTO) {
        final TimeLogEntity entity = timeLogRepository.findByCreatedBy(timeLogDTO.getCreatedBy())
                .stream()
                .filter(e -> timeLogDTO.getId().equals(e.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }


    @Override
    public List<TimeLogDTO> findByTeam(Long teamId) {
        if (Objects.isNull(teamId)) {
            return mapper.mapAsList(timeLogRepository.findAll(), TimeLogDTO.class);
        }
        List<Long> userIds = teamMemberService.getForTeam(teamId).stream()
                .map(member -> member.getUser().getId())
                .collect(Collectors.toList());
        List<String> userEmails = userIds.stream()
                .map(id -> userService.getUserById(id).getEmail())
                .collect(Collectors.toList());
        return mapper.mapAsList(timeLogRepository.findByUserList(userEmails), TimeLogDTO.class);
    }

    @Override
    public void delete(TimeLogDTO timeLogDTO) {
        timeLogRepository.deleteById(timeLogDTO.getId());
    }
}
