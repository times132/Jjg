package com.jejujg.controller;

import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> list(){
        return null;
    }

    @GetMapping("/{bid}")
    public ResponseEntity<BoardResponse> read(@PathVariable("bid") Long bid){
        return new ResponseEntity<>(boardService.findOne(bid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> write(@RequestBody BoardRequest request){

        return new ResponseEntity<>(boardService.save(request), HttpStatus.OK);
    }

    @PutMapping("/{bid}")
    public ResponseEntity<?> modify(@PathVariable("bid") Long bid, @RequestBody BoardRequest request){

        return new ResponseEntity<>(boardService.update(bid, request), HttpStatus.OK);
    }

    @DeleteMapping("/{bid}")
    public ResponseEntity<?> remove(){
        return null;
    }
}
