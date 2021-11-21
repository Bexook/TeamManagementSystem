package com.example.TeamManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandling {


    @ExceptionHandler({
            AuthenticationException.class,
            org.apache.tomcat.websocket.AuthenticationException.class,
            org.springframework.security.core.AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void unauthorized(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(ErrorStatusCode.UNAUTHORIZED.getStatusCode());
    }


    @ExceptionHandler(AuthorizationServiceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void forbidden(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(ErrorStatusCode.FORBIDDEN.getStatusCode());
    }

    @ExceptionHandler({SystemException.class})
    public void custom(HttpServletResponse httpServletResponse, SystemException systemException) throws IOException {
        httpServletResponse.sendError(systemException.errorCode);
    }
}
