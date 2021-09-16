package com.example.TeamManagementSystem.model;

import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.SearchCriteria;
import lombok.Data;

@Data
public class SearchStrategy {
    private SearchCriteria searchCriteria;
    private Object value;
}
