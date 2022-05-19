package com.example.rentme_backend_morgan.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.rentme_backend_morgan.security.services.BadRequestException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

public class ControllerAdviceExceptionHandler extends RuntimeException {

    public ResponseEntity exceptionHandler(RuntimeException ex, HttpServletRequest request) {
        String payload = "path: " + request.getRequestURI() + "; msg: " + ex.getMessage();
        return new ResponseEntity(payload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity exceptionHandlerValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity(ex.getFieldError().getField() + " is does not match requirements ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity exceptionHandlerValid(ConstraintViolationException ex) {
        return new ResponseEntity(ex.getLocalizedMessage() + " is does not match requirements ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity exceptionHandler(HttpClientErrorException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccountFoundException.class})
    public AccountFoundException accountFoundException(HttpClientErrorException ex) {
        return new AccountFoundException(ex.getMessage());
    }

//    @ExceptionHandler({SQLException.class})
//    public ResponseEntity exceptionHandlerValid(PSQLException ex) {
//        String[] err = ex.getMessage().split("Detail:");
//        return new ResponseEntity(err[1], HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler({MysqlDataTruncation.class})
//    public ResponseEntity exceptionHandlerSQL(MysqlDataTruncation ex) {
//        String[] err = ex.getMessage().split("Detail:");
//        return new ResponseEntity(err[1], HttpStatus.BAD_REQUEST);
//    }

}
