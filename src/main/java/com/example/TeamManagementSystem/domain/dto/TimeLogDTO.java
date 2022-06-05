package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.enumTypes.CalendarDayType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeLogDTO extends BaseDTO {

    private Long id;
    private LocalDate date;
    private Double timeSpend;
    private CalendarDayType calendarDayType;
    private TeamDTO teamDTO;
    private String createdBy;

}
