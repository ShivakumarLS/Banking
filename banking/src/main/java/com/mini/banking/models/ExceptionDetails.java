package com.mini.banking.models;

import java.time.Instant;

public class ExceptionDetails {
    private String message;
    private Instant timeStamp;
    private String details;
    
    public ExceptionDetails(String message, Instant timeStamp, String details) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.details = details;
    }

    
}
