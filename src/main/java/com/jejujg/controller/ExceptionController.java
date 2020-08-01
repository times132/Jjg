package com.jejujg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> BadRequestException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
