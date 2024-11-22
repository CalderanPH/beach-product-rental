package com.salsatechnology.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> handleApiException(ApiException ex) {
        ResponseError error = new ResponseError(ex.getMessage());
        return new ResponseEntity<>(error, ex.getStatus());
    }

}