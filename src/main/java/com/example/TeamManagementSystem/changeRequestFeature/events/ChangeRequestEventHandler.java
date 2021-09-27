package com.example.TeamManagementSystem.changeRequestFeature.events;

import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeRequestEventHandler {


    @Autowired
    private Sources<Long> sources;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;


    public void apply(ChangeRequestEvent changeRequestEvent) {
        switch (changeRequestEvent.getChangeRequestEventDetails().getOperationType()) {
            case CREATE: {
                break;
            }
            case UPDATE: {
                break;
            }
            case DELETE: {
                break;
            }
            case READ: {
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }


}
