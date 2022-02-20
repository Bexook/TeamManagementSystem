package com.example.TeamManagementSystem.changeRequestFeature.events.publishers;

import com.example.TeamManagementSystem.changeRequestFeature.events.ChangeRequestEvent;
import lombok.NonNull;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChangeRequestEventPublisher implements ApplicationEventPublisher {

    public void publishEvent(@NonNull ChangeRequestEvent event) {
        ApplicationEventPublisher.super.publishEvent(event);
    }

    @Override
    public void publishEvent(@NonNull Object event) {

    }
}
