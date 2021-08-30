package com.example.petProject.changeRequestFeature.repository;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestCommentRepository extends JpaRepository<ChangeRequestCommentEntity, Long> {
}