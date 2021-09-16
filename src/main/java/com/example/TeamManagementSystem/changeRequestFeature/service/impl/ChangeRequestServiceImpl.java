package com.example.TeamManagementSystem.changeRequestFeature.service.impl;

import com.example.TeamManagementSystem.changeRequestFeature.SearchFunction;
import com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.eventPublisher.ChangeRequestEventPublisher;
import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestEventDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.SearchCriteria;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
    private ChangeRequestEventPublisher changeRequestEventPublisher;
    @Autowired
    private EntityManager entityManager;

    private Map<SearchCriteria, SearchFunction<ChangeRequestRepository, ChangeRequestEntity>> searchFunctionsMap;

    {
        searchFunctionsMap = new HashMap<>();
        searchFunctionsMap.put(SearchCriteria.BY_CREATION_DATE, (object, repo, pageable) -> repo.findByCreatedAt((Date) object, pageable));
        searchFunctionsMap.put(SearchCriteria.BY_USERNAME, (object, repo, pageable) -> repo.findByCreatedBy(String.valueOf(object), pageable));
        searchFunctionsMap.put(SearchCriteria.BY_OBJECT_TYPE, (object, repo, pageable) -> repo.findByObjectType(String.valueOf(object), pageable));
        searchFunctionsMap.put(SearchCriteria.BY_LAST_MODIFICATION_DATE, (object, repo, pageable) -> repo.findByModifiedAt((Date) object, pageable));
        searchFunctionsMap.put(SearchCriteria.BY_MODIFIED_BY, (object, repo, pageable) -> repo.findByModifiedBy(String.valueOf(object), pageable));
    }

    @Override
    @Transactional
    @PreAuthorize("@userAccessValidation.hasAuthority('APPROVE')")
    public ChangeRequestReviewDTO update(ChangeRequestReviewDTO changeRequestReviewDTO) throws ClassNotFoundException {
        ChangeRequestEntity cr = changeRequestRepository.getById(changeRequestReviewDTO.getChangeRequestEntity().getId());
        proceedChangeRequest(changeRequestReviewDTO, cr);
        changeRequestRepository.save(cr);
        changeRequestReviewDTO.setChangeRequestEntity(cr);
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


    private void proceedChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) throws ClassNotFoundException {
        switch (changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState()) {
            case APPROVED: {
                approveChangeRequest(changeRequestReviewDTO, cr);
                break;
            }
            case DECLINED: {
                declineChangeRequest(changeRequestReviewDTO, cr);
                break;
            }
            case REQUESTED_CHANGES: {
                requestChanges(changeRequestReviewDTO, cr);
                break;
            }
            case PENDING: {
                changeRequestEventPublisher.publishChangeRequestEvent(this, new ChangeRequestEventDTO("PENDING change request", ChangeRequestEventType.UPDATE, cr));
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private void requestChanges(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) {
        changeRequestCommentService.addAllComments(changeRequestReviewDTO.getComment());
        changeRequestEventPublisher.publishChangeRequestEvent(this, new ChangeRequestEventDTO("Require changes", ChangeRequestEventType.NOT_VALID, cr));
    }

    private void declineChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) {
        cr.setChangeRequestState(changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState());
        cr.setRelevant(false);
        ChangeRequestEventDTO changeRequestEventDTO = new ChangeRequestEventDTO("Change Request  has been DECLINED!", ChangeRequestEventType.DECLINE, cr);
        changeRequestEventPublisher.publishChangeRequestEvent(this, changeRequestEventDTO);
    }


    private void approveChangeRequest(ChangeRequestReviewDTO changeRequestReviewDTO, ChangeRequestEntity cr) throws ClassNotFoundException {
        cr.setChangeRequestState(changeRequestReviewDTO.getChangeRequestEntity().getChangeRequestState());
        JpaRepository jpaRepository = (JpaRepository) entityManager.unwrap(Class.forName(cr.getObjectRepo()));
        jpaRepository.save(cr);
        ChangeRequestEventDTO changeRequestEventDTO = new ChangeRequestEventDTO("Change Request  has been APPROVED!", ChangeRequestEventType.UPDATE, cr);
        changeRequestEventPublisher.publishChangeRequestEvent(this, changeRequestEventDTO);
    }

}

