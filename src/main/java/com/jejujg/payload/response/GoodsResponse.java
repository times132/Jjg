package com.jejujg.payload.response;

import com.jejujg.model.Image;
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
    private Image image;

    @Builder
    public GoodsResponse(Long bid, String title, String content, Integer price, String writer, Image image) {
        this.bid = bid;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.image = image;
    }
}
