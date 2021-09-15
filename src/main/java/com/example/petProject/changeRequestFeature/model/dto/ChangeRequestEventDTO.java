package com.example.petProject.changeRequestFeature.model.dto;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestEventDTO {

    private String message;
    private ChangeRequestEventType changeRequestEventType;
    private ChangeRequestEntity changeRequestEntity;

}
