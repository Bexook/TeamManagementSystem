package com.example.TeamManagementSystem.model.enumTypes;

import java.util.Arrays;

public enum TeamMemberRole {

    FRONTEND(1),
    BACKEND(2),
    PM(3),
    DEVOPS(4),
    PENDING(5),
    NONE(6);

    private int code;

    TeamMemberRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public static TeamMemberRole getByCode(int code){
        return Arrays.stream(values()).filter(element->element.getCode() == code).findFirst().orElseThrow();
    }


}
