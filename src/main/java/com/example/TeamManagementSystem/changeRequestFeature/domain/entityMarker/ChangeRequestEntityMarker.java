package com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker;

import java.io.Serializable;

public interface ChangeRequestEntityMarker extends Serializable {
    <T extends Number> T getId();
}
