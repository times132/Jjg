package com.jejujg.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController implements ErrorController {

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<Object> BadRequestException(RuntimeException e){
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
