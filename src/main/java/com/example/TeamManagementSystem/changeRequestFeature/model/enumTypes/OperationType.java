package com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes;

import java.util.Arrays;

public enum OperationType {
    CREATE(1),
    UPDATE(2),
    DELETE(3),
    READ(4);

    private int code;

    OperationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OperationType getByCode(int code) {
        return Arrays.stream(values()).filter(element -> element.getCode() == code).findFirst().orElseThrow();
    }
}
