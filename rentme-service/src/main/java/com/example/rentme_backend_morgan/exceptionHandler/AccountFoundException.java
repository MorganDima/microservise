package com.example.rentme_backend_morgan.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountFoundException extends RuntimeException {
    public AccountFoundException(String msg) {
        super(msg);
    }
}