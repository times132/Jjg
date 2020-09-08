package com.jejujg.controller;

import com.jejujg.common.Criteria;
import com.jejujg.common.Pagination;
import com.jejujg.model.Image;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.service.CategoryService;
import com.jejujg.service.GoodsService;
import com.jejujg.service.UploadService;
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
    private final UploadService uploadService;
    private final CategoryService categoryService;

    @GetMapping("/{categoryNum}")
    public ResponseEntity<Page> list(@PathVariable("categoryNum") String categoryNum, Criteria criteria){

        Page page = goodsService.list(criteria, categoryService.findOne(categoryNum));
        Pagination pagination = Pagination.builder()
                .criteria(criteria)
                .pagingSize(criteria.getPageSize())
                .realEndPage(page.getTotalPages())
                .total(page.getTotalElements())
                .build();

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/detail/{gid}")
    public ResponseEntity<GoodsResponse> read(@PathVariable("gid") Long gid){
        return new ResponseEntity<>(goodsService.findOne(gid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> write(@RequestBody GoodsRequest request){
        Image image = uploadService.saveGoodsDB(request.getImage());
        return new ResponseEntity<>(goodsService.save(request, image), HttpStatus.OK);
    }

    @PutMapping("/{gid}")
    public ResponseEntity<?> modify(@PathVariable("gid") Long gid, @RequestBody GoodsRequest request){
        Image image = uploadService.saveGoodsDB(request.getImage());
        return new ResponseEntity<>(goodsService.update(gid, request, image), HttpStatus.OK);
    }

    @DeleteMapping("/{gid}")
    public ResponseEntity<?> remove(@PathVariable("gid") Long gid){
        return new ResponseEntity<>(goodsService.delete(gid), HttpStatus.OK);
    }
}
