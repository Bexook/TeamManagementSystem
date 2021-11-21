package com.example.TeamManagementSystem.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionModel extends Exception {
    private String message;
    private ErrorStatusCode httpStatus;

    public ExceptionModel(String message, ErrorStatusCode httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ExceptionModel(String message, String message1, ErrorStatusCode httpStatus) {
        super(message);
        this.message = message1;
        this.httpStatus = httpStatus;
    }

    public ExceptionModel(String message, Throwable cause, String message1, ErrorStatusCode httpStatus) {
        super(message, cause);
        this.message = message1;
        this.httpStatus = httpStatus;
    }

    public ExceptionModel(Throwable cause, String message, ErrorStatusCode httpStatus) {
        super(cause);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ExceptionModel(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, ErrorStatusCode httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.httpStatus = httpStatus;
    }
}
