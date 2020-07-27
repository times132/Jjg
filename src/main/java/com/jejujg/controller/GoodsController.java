package com.jejujg.controller;

import com.jejujg.common.Criteria;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.service.CategoryService;
import com.jejujg.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final CategoryService categoryService;

    @GetMapping("/{categoryNum}")
    public ResponseEntity<Page> list(@PathVariable("categoryNum") String categoryNum, Criteria criteria){

        Page page = goodsService.list(criteria, categoryService.findOne(categoryNum));

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/detail/{bid}")
    public ResponseEntity<GoodsResponse> read(@PathVariable("bid") Long bid){
        return new ResponseEntity<>(goodsService.findOne(bid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> write(@RequestBody GoodsRequest request){

        return new ResponseEntity<>(goodsService.save(request), HttpStatus.OK);
    }

    @PutMapping("/{bid}")
    public ResponseEntity<?> modify(@PathVariable("bid") Long bid, @RequestBody GoodsRequest request){

        return new ResponseEntity<>(goodsService.update(bid, request), HttpStatus.OK);
    }

    @DeleteMapping("/{bid}")
    public ResponseEntity<?> remove(@PathVariable("bid") Long bid){
        return new ResponseEntity<>(goodsService.delete(bid), HttpStatus.OK);
    }
}
