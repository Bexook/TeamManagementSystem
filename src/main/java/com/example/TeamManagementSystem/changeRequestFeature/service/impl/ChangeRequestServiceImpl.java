package com.example.TeamManagementSystem.changeRequestFeature.service.impl;

import com.example.TeamManagementSystem.changeRequestFeature.SearchFunction;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.changeRequestFeature.domain.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.SearchCriteria;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestService;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChangeRequestServiceImpl implements ChangeRequestService {


    @Autowired
    private ChangeRequestRepository changeRequestRepository;
    @Autowired
    private ChangeRequestCommentService changeRequestCommentService;
    @Autowired
    private ObjectMapper serializer;
    @Autowired
    private OrikaBeanMapper mapper;
    @Autowired
    private Sources<Long> sources;

    private Map<SearchCriteria, SearchFunction<ChangeRequestRepository, ChangeRequestEntity>> searchFunctionsMap;

    {
        searchFunctionsMap = new HashMap<>();
        searchFunctionsMap.put(SearchCriteria.BY_CREATION_DATE, (object, repo, pageable) -> repo.findByCreatedAt((LocalDateTime) object, pageable));
        searchFunctionsMap.put(SearchCriteria.BY_USERNAME, (object, repo, pageable) -> repo.findByCreatedBy(String.valueOf(object), pageable));
        searchFunctionsMap.put(SearchCriteria.BY_OBJECT_TYPE, (object, repo, pageable) -> repo.findByDomainClass(String.valueOf(object), pageable));
        searchFunctionsMap.put(SearchCriteria.BY_LAST_MODIFICATION_DATE, (object, repo, pageable) -> repo.findByModifiedAt((Date) object, pageable));
        searchFunctionsMap.put(SearchCriteria.BY_MODIFIED_BY, (object, repo, pageable) -> repo.findByModifiedBy(String.valueOf(object), pageable));
    }


    @Override
    @SneakyThrows
    @Transactional
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE')")
    public ChangeRequestReviewDTO update(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException {
        proceedChangeRequest(changeRequestReviewDTO);
        return changeRequestReviewDTO;
    }

    @Override
    public ChangeRequestEntity getById(Long id) {
        return changeRequestRepository.getById(id);
    }

    @Override
    public Page<ChangeRequestEntity> getByCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable) {
        return searchFunctionsMap.get(searchCriteria).apply(searchValue, changeRequestRepository, pageable);
    }

    @Override
    public Page<ChangeRequestEntity> findAll(Pageable pageable) {
        return changeRequestRepository.findAll(pageable);
    }


    private void proceedChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException, JsonProcessingException {
        switch (changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState()) {
            case APPROVED: {
                approveChangeRequest(changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState(), changeRequestReviewDTO.getChangeRequestEntity());
                break;
            }
            case DECLINED: {
                declineChangeRequest(changeRequestReviewDTO, changeRequestReviewDTO.getChangeRequestEntity());
                break;
            }
            case REQUESTED_CHANGES: {
                requestChanges(changeRequestReviewDTO, changeRequestReviewDTO.getChangeRequestEntity());
                break;
            }
            case PENDING: {
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private void requestChanges(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) {
        changeRequestCommentService.addAllComments(changeRequestReviewDTO.getComment());
    }

    private void declineChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) {
        cr.setChangeRequestState(changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState());
        cr.setRelevant(false);
    }


    private <T> void approveChangeRequest(ChangeRequestState changeRequestState, ChangeRequestEntity cr) throws ClassNotFoundException, JsonProcessingException {
        cr.setChangeRequestState(changeRequestState);
        approveByOperationType(cr);
        changeRequestRepository.save(cr);
    }


    private <T> void approveByOperationType(ChangeRequestEntity changeRequestEntity) throws ClassNotFoundException, JsonProcessingException {

        switch (changeRequestEntity.getOperationType()) {
            case READ: {
                break;
            }
            case CREATE:
            case UPDATE: {
                Class<T> domainClass = (Class<T>) Class.forName(changeRequestEntity.getDomainClass());
                ChangeRequestEntityMarker entityMarker = (ChangeRequestEntityMarker) mapper.map(serializer.readValue(changeRequestEntity.getNewObjectState(), domainClass), domainClass);
                sources.loadRepository(domainClass).save(entityMarker);
                break;
            }
            case DELETE: {
                Class<T> domainClass = (Class<T>) Class.forName(changeRequestEntity.getDomainClass());
                ChangeRequestEntityMarker entityMarker = (ChangeRequestEntityMarker) mapper.map(serializer.readValue(changeRequestEntity.getCurrentObjectState(), domainClass), domainClass);
                sources.loadRepository(domainClass).deleteById(entityMarker.getId());
                break;
            }
        }
    }
}

