package com.example.petProject.changeRequestFeature.changeRequestEvents.eventListener;

import com.example.petProject.changeRequestFeature.changeRequestEvents.ChangeRequestOperationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeRequestEventListener {


    @Autowired


    @EventListener(ChangeRequestOperationEvent.class)
    public void changeRequestOperationEventListener(ChangeRequestOperationEvent event) {

    }

}
