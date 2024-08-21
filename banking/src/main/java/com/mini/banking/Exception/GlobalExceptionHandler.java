package com.mini.banking.Exception;

import java.net.http.HttpResponse;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.mini.banking.models.ExceptionDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandling(Exception exception, WebRequest request) {
		ExceptionDetails errorDetails = new ExceptionDetails( exception.getMessage(),Instant.now(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(ExceptionHandling.class)
    public ResponseEntity<?> handle(Exception exception , WebRequest request){
        return new ResponseEntity<>(new ExceptionDetails(exception.getMessage(),Instant.now(),request.getDescription(false)), HttpStatus.NOT_FOUND);
     }

}
