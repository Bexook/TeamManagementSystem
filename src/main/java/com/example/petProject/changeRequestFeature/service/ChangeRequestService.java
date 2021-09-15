package com.example.petProject.changeRequestFeature.service;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.SearchCriteria;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChangeRequestService {

    ChangeRequestReviewDTO update(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException;

    ChangeRequestEntity getById(Long id);

    Page<ChangeRequestEntity> getByCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable);

    Page<ChangeRequestEntity> findAll(Pageable pageable);

}
