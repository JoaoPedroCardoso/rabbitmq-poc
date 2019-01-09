package com.poc.rabbitmq.infrastruct.exception.handller;

import com.poc.rabbitmq.infrastruct.exception.ObjectNotFoundException;
import com.poc.rabbitmq.infrastruct.exception.response.ErrorMessageResponse;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<ErrorMessageResponse> handleObjectNotFoundException(ObjectNotFoundException exception) {

        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.of(
                exception.getMessage());

        log.warning("ObjectNotFound exception: " + errorMessageResponse.getMessage());

        return ResponseEntity.badRequest().body(errorMessageResponse);
    }

}