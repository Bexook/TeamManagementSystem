package com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes;

public enum SearchCriteria {

    BY_USERNAME("created_by"),
    BY_OBJECT_TYPE("object_type"),
    BY_LAST_MODIFICATION_DATE("modified_at"),
    BY_CREATION_DATE("created_at"),
    BY_MODIFIED_BY("modified_by"),
    BY_CHANGE_REQUEST("change_request_id");


    private String value;

    SearchCriteria(String value) {
        this.value = value;
    }

    public String getValue() {
        return value.toLowerCase();
    }
}
