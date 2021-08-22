package com.example.petProject.changeRequestFeature.service;

import com.example.petProject.changeRequestFeature.entity.ChangeRequestEntity;

import java.util.List;

public interface ChangeRequestService {

    void save(ChangeRequestEntity changeRequestEntity);

    void update(ChangeRequestEntity changeRequestEntity);

    ChangeRequestEntity getById(Long id);

    List<ChangeRequestEntity> getByUsername(String username);

}
