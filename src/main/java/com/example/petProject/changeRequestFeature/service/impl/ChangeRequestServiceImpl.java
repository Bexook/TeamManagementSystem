package com.example.petProject.changeRequestFeature.service.impl;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.repository.ChangeRequestRepository;
import com.example.petProject.changeRequestFeature.service.ChangeRequestCommentService;
import com.example.petProject.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChangeRequestServiceImpl implements ChangeRequestService {


    @Autowired
    private ChangeRequestRepository changeRequestRepository;
    @Autowired
    private ChangeRequestCommentService changeRequestCommentService;

    @Override
    @Transactional
    public void save(ChangeRequestEntity changeRequestEntity) {

    }

    @Override
    @Transactional
    public void update(ChangeRequestEntity changeRequestEntity) {

    }

    @Override
    public ChangeRequestEntity getById(Long id) {
        return null;
    }

    @Override
    public List<ChangeRequestEntity> getByUsername(String username) {
        return null;
    }
}
