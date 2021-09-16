package com.example.TeamManagementSystem.changeRequestFeature.service;

import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.SearchCriteria;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface ChangeRequestService {

    ChangeRequestReviewDTO update(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException;

    ChangeRequestEntity getById(Long id);

    Page<ChangeRequestEntity> getByCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable);

    Page<ChangeRequestEntity> findAll(Pageable pageable);

}
