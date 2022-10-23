package com.cleancode.bsimpl.exceptionsmanagement;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RequestExceptionInterceptorResponse extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { CleanCodeException.class})
    protected ResponseEntity<Object> handleConflict(
            CleanCodeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getResponse(),
                new HttpHeaders(), ex.getResponse().httpResponseStatus(), request);
    }
}