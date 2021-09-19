package com.example.TeamManagementSystem.changeRequestFeature.repository;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequestEntity, Long> {

    Page<ChangeRequestEntity> findByCreatedBy(String createdBy, Pageable pageable);

    Page<ChangeRequestEntity> findByCreatedAt(Date createdAt, Pageable pageable);

    Page<ChangeRequestEntity> findByDomainClass(String domainClass, Pageable pageable);

    Page<ChangeRequestEntity> findByModifiedAt(Date modifiedAt, Pageable pageable);

    Page<ChangeRequestEntity> findByModifiedBy(String modifiedBy, Pageable pageable);

}
