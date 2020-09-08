package com.jejujg.payload.response;

import com.jejujg.model.Image;
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
    private String title;
    private String content;
    private Integer price;
    private String writer;
    private Image image;
}
