package com.example.petProject.changeRequestFeature.model.enumTypes;

import java.util.Arrays;

public enum ChangeRequestState {

    PENDING(1),
    APPROVED(2),
    REQUESTED_CHANGES(3),
    DECLINED(4);

    private int code;

    ChangeRequestState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ChangeRequestState getByCode(int code) {
        return Arrays.stream(values()).filter(element -> element.getCode() == code).findFirst().orElseThrow();
    }

}
