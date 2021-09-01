package com.example.petProject.changeRequestFeature.changeRequestEvents;

import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import org.springframework.context.ApplicationEvent;

public class ChangeRequestOperationEvent extends ApplicationEvent {

    private String message;
    private ChangeRequestEventType changeRequestEventType;

    public ChangeRequestOperationEvent(Object source,
                                       String message,
                                       ChangeRequestEventType changeRequestEventType) {
        super(source);
        this.message = message;
        this.changeRequestEventType = changeRequestEventType;
    }
}

