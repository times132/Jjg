package com.jejujg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/page/{page}")
    public ResponseEntity<?> list(){
        return null;
    }

    @GetMapping("/{bid}")
    public ResponseEntity<?> read(){
        return null;
    }

    @PostMapping("/{bid}")
    public ResponseEntity<?> write(){
        return null;
    }

    @PutMapping("/{bid}")
    public ResponseEntity<?> modify(){
        return null;
    }

    @DeleteMapping("/{bid}")
    public ResponseEntity<?> remove(){
        return null;
    }
}
