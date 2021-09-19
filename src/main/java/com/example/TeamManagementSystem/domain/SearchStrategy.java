package com.example.TeamManagementSystem.domain;

import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.SearchCriteria;
import lombok.Data;

@Data
public class SearchStrategy {
    private SearchCriteria searchCriteria;
    private Object value;
}
