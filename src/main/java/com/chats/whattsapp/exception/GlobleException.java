package com.chats.whattsapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobleException{

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetail> userExceptionHandler(UserException ex, WebRequest req) {

        ErrorDetail err = new ErrorDetail(
            ex.getMessage(),
            req.getDescription(false),
            LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetail> messageExceptionHandler(MessageException ex, WebRequest req) {

        ErrorDetail err = new ErrorDetail(
            ex.getMessage(),
            req.getDescription(false),
            LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest req) {

        String error = ex.getBindingResult().getFieldError().getDefaultMessage();

        ErrorDetail err = new ErrorDetail(
            "Validation Error",
            error,
            LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetail> noHandlerFoundExceptionHandler(NoHandlerFoundException ex, WebRequest req) {

        ErrorDetail err = new ErrorDetail(
            "Endpoint not found",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetail>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> otherExceptionHandler(Exception ex, WebRequest req) {

        ErrorDetail err = new ErrorDetail(
            ex.getMessage(),
            req.getDescription(false),
            LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
    }

}
