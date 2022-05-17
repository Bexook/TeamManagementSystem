package com.example.TeamManagementSystem.changeRequestFeature.service;


import com.tms.common.changeRequestDomain.dto.ChangeRequestReviewDTO;
import com.tms.common.changeRequestDomain.entity.ChangeRequestEntity;
import com.tms.common.changeRequestDomain.enumTypes.SearchCriteria;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface ChangeRequestService {

    ChangeRequestReviewDTO update(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException;

    ChangeRequestEntity getById(Long id);

    Page<ChangeRequestEntity> getByCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable);

    Page<ChangeRequestEntity> findAll(Pageable pageable);

}
