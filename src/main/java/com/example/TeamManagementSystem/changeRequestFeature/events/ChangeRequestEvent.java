package com.example.TeamManagementSystem.changeRequestFeature.events;

import com.example.TeamManagementSystem.changeRequestFeature.events.dto.ChangeRequestEventDetails;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class ChangeRequestEvent extends ApplicationEvent {

    private ChangeRequestEventDetails changeRequestEventDetails;

    public ChangeRequestEvent(Object source) {
        super(source);
    }

    public ChangeRequestEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public ChangeRequestEvent(Object source, ChangeRequestEventDetails changeRequestEventDetails) {
        super(source);
        this.changeRequestEventDetails = changeRequestEventDetails;
    }

    public ChangeRequestEvent(Object source, Clock clock, ChangeRequestEventDetails changeRequestEventDetails) {
        super(source, clock);
        this.changeRequestEventDetails = changeRequestEventDetails;
    }

    public ChangeRequestEventDetails getChangeRequestEventDetails() {
        return changeRequestEventDetails;
    }

    public void setChangeRequestEventDetails(ChangeRequestEventDetails changeRequestEventDetails) {
        this.changeRequestEventDetails = changeRequestEventDetails;
    }
}
