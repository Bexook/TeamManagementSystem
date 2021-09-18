package com.example.TeamManagementSystem.exception;

import org.apache.http.HttpException;

public class RequestApproval extends HttpException {

    public RequestApproval() {
    }

    public RequestApproval(String message) {
        super(message);
    }

    public RequestApproval(String message, Throwable cause) {
        super(message, cause);
    }


}
