package com.example.petProject.changeRequestFeature.repository;

import com.example.petProject.changeRequestFeature.entity.ChangeRequestComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestCommentRepository extends JpaRepository<ChangeRequestComment, Long> {
}
