package com.example.petProject.model;

import com.example.petProject.changeRequestFeature.model.enumTypes.SearchCriteria;
import lombok.Data;

@Data
public class SearchStrategy {
    private SearchCriteria searchCriteria;
    private Object value;
}
