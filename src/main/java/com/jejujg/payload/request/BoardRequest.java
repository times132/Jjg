package com.jejujg.payload.request;

import com.jejujg.model.Board;
import com.jejujg.model.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class BoardRequest {

    private String category;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<File> files;

    @Builder
    public BoardRequest(String category, String title, String content, Integer price, String writer, List<File> files) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.files = files;
    }

    public Board toEntity(){
        return Board.builder()
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .price(this.price)
                .writer(this.writer)
                .files(this.files)
                .build();
    }
}
