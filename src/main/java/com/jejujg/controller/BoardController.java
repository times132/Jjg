package com.jejujg.controller;

import com.jejujg.common.Criteria;
import com.jejujg.common.Pagination;
import com.jejujg.model.Image;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardListResponse;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.service.CategoryService;
import com.jejujg.service.BoardService;
import com.jejujg.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final UploadService uploadService;
    private final CategoryService categoryService;

    // 세부 카테고리 제품 리스트
    @GetMapping("/{categoryNum}")
    public ResponseEntity<BoardListResponse> boardListGET(@PathVariable("categoryNum") String categoryNum, Criteria criteria){

        Page page = boardService.list(criteria, categoryService.findOne(categoryNum));
        Pagination pagination = Pagination.builder()
                .criteria(criteria)
                .pageRange(criteria.getPageRange())
                .realEndPage(page.getTotalPages())
                .total(page.getTotalElements())
                .build();

        BoardListResponse boardListResponse = BoardListResponse.builder()
                .boardList(page.getContent())
                .pagination(pagination)
                .build();

        return new ResponseEntity<>(boardListResponse, HttpStatus.OK);
    }

//    @GetMapping("/{categoryName}")
//    public ResponseEntity<ArrayList<Goods>> mainCategoryList(@PathVariable("categoryName") String categoryName){
//        ArrayList<Goods> mainCategoryGoods = categoryService.findMainCategoryGoods(categoryName);
//    }

    // 제품 상세 페이지
    @GetMapping("/detail/{bid}")
    public ResponseEntity<BoardResponse> boardGET(@PathVariable("bid") Long bid){
        return new ResponseEntity<>(boardService.findOne(bid), HttpStatus.OK);
    }

    // 제품 작성
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> boardPOST(@RequestBody BoardRequest request){
        Image image = null;
        if (request.getImage() != null) {
            image = uploadService.saveBoardImageDB(request.getImage());
        }

        return new ResponseEntity<>(boardService.save(request, image).getBid(), HttpStatus.OK);
    }

    // 제품 수정
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{bid}")
    public ResponseEntity<?> boardPUT(@PathVariable("bid") Long bid, @RequestBody BoardRequest request){
        Image image = null;
        Image oldImage = boardService.findImage(bid);

        if (request.getImage() != null) { // 요청에 이미지가 있을 때
            if (oldImage == null) { // 기존에 이미지가 없을 경우(삭제할 이미지가 없음)
                image = uploadService.updateBoardImageDB(Long.valueOf(String.valueOf(request.getImage().get("fid"))), request.getImage());
            } else if(!request.getImage().get("fid").equals(oldImage.getFid())) { // 기존 이미지랑 다를 경우
                uploadService.deleteBoardImage(oldImage.getUuid() + "_" + oldImage.getFileName(), oldImage.getPath());
                image = uploadService.updateBoardImageDB(Long.valueOf(String.valueOf(request.getImage().get("fid"))), request.getImage());
            } else { // 변화가 없는 경우
                image = oldImage;
            }
        }else { // 이미지가 없을 경우
            if (oldImage != null){ // 기존 이미지를 삭제 할 경우
                uploadService.deleteBoardImage(oldImage.getUuid() + "_" + oldImage.getFileName(), oldImage.getPath());
            }
        }

        return new ResponseEntity<>(boardService.update(bid, request, image), HttpStatus.OK);
    }

    // 제품 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{bid}")
    public ResponseEntity<?> boardDEL(@PathVariable("bid") Long bid){
        Image image = boardService.findImage(bid);
        uploadService.deleteBoardImage(image.getUuid() + "_" + image.getFileName(), image.getPath());
        return new ResponseEntity<>(boardService.delete(bid), HttpStatus.OK);
    }
}
