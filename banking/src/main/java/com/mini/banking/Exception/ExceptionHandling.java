package com.mini.banking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExceptionHandling extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public ExceptionHandling(String message){    
        super(message);
    }

}
