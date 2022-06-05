package com.example.TeamManagementSystem.domain.enumTypes;

import java.util.Arrays;

public enum TeamMemberRole {

    FRONTEND(0),
    BACKEND(1),
    PM(2),
    DEVOPS(3),
    PENDING(4),
    NONE(5);

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
