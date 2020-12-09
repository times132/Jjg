package com.jejujg.helper;

import com.jejujg.model.Board;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import com.jejujg.model.User;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.service.BoardService;
import com.jejujg.service.CategoryService;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class BoardTestHelper {

    private BoardService boardService;
    private CategoryService categoryService;

    public BoardRequest.BoardRequestBuilder makeBoard(User user, String title, String content, String categoryNum) {
        CategoryItem categoryItem = categoryService.findOne(categoryNum);

        return BoardRequest.builder()
                .title(title)
                .content(content)
                .price(2345)
                .categoryItem(categoryItem)
                .writer(user.getUsername());
    }

    public Board createBoard(User user, String title, String content, String categoryNum) {
        return boardService.save(makeBoard(user, title, content, categoryNum).build(), null);
    }

    public Board createBoard(User user, String title, String content, String categoryNum, Image image) {
        return boardService.save(makeBoard(user, title, content, categoryNum).build(), image);
    }
}
