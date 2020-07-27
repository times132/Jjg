package com.jejujg.payload.response;

import com.jejujg.model.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GoodsResponse {

    private Long bid;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<File> files;

    @Builder
    public GoodsResponse(Long bid, String title, String content, Integer price, String writer, List<File> files) {
        this.bid = bid;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.files = files;
    }
}
