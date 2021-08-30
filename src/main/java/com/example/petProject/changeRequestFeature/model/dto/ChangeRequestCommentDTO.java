package com.example.petProject.changeRequestFeature.model.dto;

import com.example.petProject.changeRequestFeature.model.entity.ChangeRequestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestCommentDTO {

    private Long id;

    private String comment;

    private String commentBy;

    private ChangeRequestEntity changeRequestEntity;

}
