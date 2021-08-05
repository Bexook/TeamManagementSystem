package com.example.petProject.changeRequestFeature.repository;

import com.example.petProject.changeRequestFeature.entity.ChangeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequestEntity, Long> {
}
