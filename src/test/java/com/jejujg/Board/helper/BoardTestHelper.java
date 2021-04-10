package com.jejujg.Board.helper;

import com.jejujg.model.*;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.repository.BoardRepository;
import com.jejujg.service.BoardService;
import com.jejujg.service.CategoryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardTestHelper {

    private BoardRepository boardRepository;
    private CategoryService categoryService;

    public BoardRequest.BoardRequestBuilder makeBoardRequest(User user, String title, String content, String categoryNum) {
        CategoryItem categoryItem = categoryService.findSubCategory(categoryNum);

        return BoardRequest.builder()
                .title(title)
                .content(content)
                .price(2345)
                .categoryItem(categoryItem)
                .writer(user.getUsername());
    }

    public CategoryItem getCategoryItem(String categoryNum) {
        return categoryService.findSubCategory(categoryNum);
    }
}
