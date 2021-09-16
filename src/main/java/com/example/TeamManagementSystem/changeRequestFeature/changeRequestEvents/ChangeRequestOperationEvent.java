package com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents;

import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestEventDTO;
import org.springframework.context.ApplicationEvent;

public class ChangeRequestOperationEvent extends ApplicationEvent {

    private ChangeRequestEventDTO changeRequestEventType;

    public ChangeRequestOperationEvent(Object source, ChangeRequestEventDTO changeRequestEventType) {
        super(source);
        this.changeRequestEventType = changeRequestEventType;
    }
}

