package com.example.TeamManagementSystem.repository;

import com.example.TeamManagementSystem.domain.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
