package com.example.TeamManagementSystem.domain;

import com.tms.dao.tmsdao.changeRequestDomain.enumTypes.SearchCriteria;
import lombok.Data;

@Data
public class SearchStrategy {
    private SearchCriteria searchCriteria;
    private Object value;
}
