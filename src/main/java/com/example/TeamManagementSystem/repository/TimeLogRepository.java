package com.example.TeamManagementSystem.repository;

import com.tms.common.domain.TimeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLogEntity, Long> {

    @Query(name = "fetchByUserAndTimeSpan", nativeQuery = true)
    List<TimeLogEntity> findByCriteria(@Param("createdBy") final String createdBy, @Param("startDate") final LocalDate start, @Param("endDate") final LocalDate end);

    @Query(name = "fetchByTimeSpan", nativeQuery = true)
    List<TimeLogEntity> findByTimeSpan(@Param("startDate") final LocalDate start, @Param("endDate") final LocalDate end);

    List<TimeLogEntity> findByCreatedBy(String email);

    @Query(name = "fetchByUserList")
    List<TimeLogEntity> findByUserList(@Param("teamMembers") final List<String> userEmails);



}
