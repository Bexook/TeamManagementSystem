package com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.eventListener;

import com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.ChangeRequestOperationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeRequestEventListener {


    @EventListener(ChangeRequestOperationEvent.class)
    public void changeRequestOperationEventListener(ChangeRequestOperationEvent event) {

    }

}
