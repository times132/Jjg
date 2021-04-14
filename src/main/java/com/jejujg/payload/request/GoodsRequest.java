package com.jejujg.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GoodsRequest {

    @JsonIgnoreProperties({"goods", "category"})
    private CategoryItem categoryItem;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<Image> imageList;
}
