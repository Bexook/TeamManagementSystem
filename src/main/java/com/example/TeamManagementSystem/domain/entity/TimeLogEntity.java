package com.example.TeamManagementSystem.domain.entity;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.domain.enumTypes.CalendarDayType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;


@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "fetchByTimeSpan", query = "SELECT * FROM time_log WHERE date => :startDate AND date <= :endDate"),
        @NamedNativeQuery(name = "fetchByUserAnsTimeSpan", query = "SELECT * FROM time_log WHERE date => :startDate AND date <= :endDate AND user_id = :userId")
})

@Data
@Entity
@Table(name = "time_log")
@EqualsAndHashCode(callSuper = true)
public class TimeLogEntity extends BaseEntity implements ChangeRequestEntityMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time_spend")
    private Double timeSpend;

    @Column(name = "calendar_day_type")
    @Enumerated(value = EnumType.ORDINAL)
    private CalendarDayType calendarDayType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
