package com.example.TeamManagementSystem.exception;

import javax.transaction.SystemException;
import java.util.Arrays;

public enum ErrorStatusCode {

    UNAUTHORIZED(401),
    FORBIDDEN(403),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);

    private int statusCode;


    public int getStatusCode() {
        return statusCode;
    }

    public ErrorStatusCode getByCode(final int code) {
        return Arrays.stream(values())
                .filter(status -> status.statusCode == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    ErrorStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
