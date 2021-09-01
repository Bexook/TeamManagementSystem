package com.example.petProject.changeRequestFeature.changeRequestEvents.eventPublisher;

import com.example.petProject.changeRequestFeature.changeRequestEvents.ChangeRequestOperationEvent;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ChangeRequestEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishChangeRequestEvent(final Object objectType,
                                          final ChangeRequestEventType changeRequestEventType,
                                          final String message) {
        applicationEventPublisher.publishEvent(new ChangeRequestOperationEvent(this, message, changeRequestEventType));
        log.info("Change Request Event published. OperationType: {}, ObjectType: {}", new Object[]{changeRequestEventType, objectType});
    }

}
