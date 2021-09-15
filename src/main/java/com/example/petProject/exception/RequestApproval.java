package com.example.petProject.exception;

public class RequestApproval extends Exception {

    public RequestApproval() {
    }

    public RequestApproval(String message) {
        super(message);
    }

    public RequestApproval(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestApproval(Throwable cause) {
        super(cause);
    }

    public RequestApproval(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
