package com.jejujg.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GoodsRequest {

    @JsonIgnoreProperties({"goods", "category"})
    private CategoryItem categoryItem;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<File> files;

    @Builder
    public GoodsRequest(CategoryItem categoryItem, String title, String content, Integer price, String writer, List<File> files) {
        this.categoryItem = categoryItem;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.files = files;
    }
}
