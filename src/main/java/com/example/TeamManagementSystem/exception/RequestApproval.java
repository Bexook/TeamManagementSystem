package com.example.TeamManagementSystem.exception;

import org.apache.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Change Request has been created!")
public class RequestApproval extends HttpException {

    private String message;
    private ErrorStatusCode errorStatusCode;

    public RequestApproval(){}

    public RequestApproval(String message, ErrorStatusCode errorStatusCode) {}

    public RequestApproval(String message) {
        super(message);
    }

    public RequestApproval(String message, Throwable cause) {
        super(message, cause);
    }

}
