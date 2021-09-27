package com.example.TeamManagementSystem.changeRequestFeature.events.listeners;

import com.example.TeamManagementSystem.changeRequestFeature.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeRequestEventListener {

    @Autowired
    private ChangeRequestService changeRequestService;


}
