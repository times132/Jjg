package com.jejujg.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import com.jejujg.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GoodsResponse {

    private Long gid;
    @JsonIgnoreProperties({"goods", "category"})
    private CategoryItem categoryItem;
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private List<Image> imageList;
//    @JsonIgnoreProperties({"board"})
//    private List<Reply> replyList;
}
