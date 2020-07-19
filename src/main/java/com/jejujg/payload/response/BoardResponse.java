package com.jejujg.payload.response;

import com.jejujg.model.Board;
import com.jejujg.model.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class BoardResponse {

    private Long bid;
    private String category;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<File> files;

    @Builder
    public BoardResponse(Long bid, String category, String title, String content, Integer price, String writer, List<File> files) {
        this.bid = bid;
        this.category = category;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.files = files;
    }

    public BoardResponse toDto(Board board){
        return BoardResponse.builder()
                .bid(board.getBid())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .price(board.getPrice())
                .writer(board.getWriter())
                .files(board.getFiles())
                .build();
    }
}
