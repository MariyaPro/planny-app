package com.prokofeva.dbuserpaservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(ProcessFailException.class)
    public ResponseEntity<Object> processFailExceptionHandle(ProcessFailException e) {
        log.error(e.getMessage());

        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(ProcessStopException.class)
    public ResponseEntity<Object> processStopExceptionHandle(ProcessStopException e) {
        log.info(e.getMessage());

        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationExceptionHandle(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        var msg = new StringBuilder("FieldErrors:\n");
        var list = e.getBindingResult().getFieldErrors();
        for (FieldError f : list)
            msg.append("\t").append(f.getField()).append(": ").append(f.getDefaultMessage()).append("\n");

        return ResponseEntity.status(400).body(msg.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandle(Exception e) {
        log.error(e.getMessage());

        return ResponseEntity.status(500).body(e.getMessage());
    }

}
