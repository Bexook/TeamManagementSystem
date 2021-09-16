package com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.eventPublisher;

import com.example.TeamManagementSystem.changeRequestFeature.changeRequestEvents.ChangeRequestOperationEvent;
import com.example.TeamManagementSystem.changeRequestFeature.model.dto.ChangeRequestEventDTO;
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
                                          final ChangeRequestEventDTO changeRequestEventDTO) {
        applicationEventPublisher.publishEvent(new ChangeRequestOperationEvent(this, changeRequestEventDTO));
        log.info("Change Request Event published. OperationType: {}, ObjectType: {}", new Object[]{changeRequestEventDTO.getChangeRequestEventType(), objectType});
    }

}
