package com.example.TeamManagementSystem.exception;

import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class ExceptionModel {
    private String message;
    private HttpStatus httpStatus;
}
