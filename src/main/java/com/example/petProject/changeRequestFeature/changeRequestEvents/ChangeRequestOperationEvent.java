package com.example.petProject.changeRequestFeature.changeRequestEvents;

import com.example.petProject.changeRequestFeature.model.dto.ChangeRequestEventDTO;
import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import org.springframework.context.ApplicationEvent;

public class ChangeRequestOperationEvent extends ApplicationEvent {

    private ChangeRequestEventDTO changeRequestEventType;

    public ChangeRequestOperationEvent(Object source, ChangeRequestEventDTO changeRequestEventType) {
        super(source);
        this.changeRequestEventType = changeRequestEventType;
    }
}

