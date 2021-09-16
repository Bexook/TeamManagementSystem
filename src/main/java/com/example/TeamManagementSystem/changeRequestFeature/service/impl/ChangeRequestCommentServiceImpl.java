package com.example.TeamManagementSystem.changeRequestFeature.service.impl;

import com.example.TeamManagementSystem.changeRequestFeature.SearchFunction;
import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestReviewDTO;
import com.example.TeamManagementSystem.changeRequestFeature.model.entity.ChangeRequestCommentEntity;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.SearchCriteria;
import com.example.TeamManagementSystem.changeRequestFeature.repository.ChangeRequestCommentRepository;
import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChangeRequestCommentServiceImpl implements ChangeRequestCommentService {

    @Autowired
    private ChangeRequestCommentRepository changeRequestCommentRepository;

    private Map<SearchCriteria, SearchFunction<ChangeRequestCommentRepository, ChangeRequestCommentEntity>> searchFunctionsMap;

    {
        searchFunctionsMap = new HashMap<>();
        searchFunctionsMap.put(SearchCriteria.BY_USERNAME, ((o, repo, pageable) -> repo.findByCommentBy(String.valueOf(o), pageable)));
        searchFunctionsMap.put(SearchCriteria.BY_CHANGE_REQUEST, ((o, repo, pageable) -> repo.findByChangeRequestEntityId((Long) o, pageable)));
    }

    @Override
    @Transactional
    public void addComment(ChangeRequestReviewDTO changeRequestReviewDTO) {
        List<ChangeRequestCommentEntity> changeRequestCommentEntity = changeRequestCommentRepository.findAll()
                .stream()
                .filter(c -> !changeRequestReviewDTO.getComment().contains(c))
                .collect(Collectors.toList());
        changeRequestCommentRepository.saveAll(changeRequestCommentEntity);
    }

    @Override
    @Transactional
    public void removeComment(ChangeRequestReviewDTO changeRequestReviewDTO) {

    }

    @Override
    public Page<ChangeRequestCommentEntity> getAllCommentCriteria(SearchCriteria searchCriteria, Object searchValue, Pageable pageable) {
        return searchFunctionsMap.get(searchCriteria).apply(searchValue, changeRequestCommentRepository, pageable);
    }

    @Override
    public List<ChangeRequestCommentEntity> addAllComments(List<ChangeRequestCommentEntity> cr) {
        changeRequestCommentRepository.saveAll(cr);
        return changeRequestCommentRepository.findAll();
    }
}
