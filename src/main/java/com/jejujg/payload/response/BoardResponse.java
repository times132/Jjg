package com.jejujg.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import com.jejujg.model.Reply;
import com.jejujg.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardResponse {

    private Long bid;
    @JsonIgnoreProperties({"goods", "category"})
    private CategoryItem categoryItem;
    private String title;
    private String content;
    private Integer price;
    @JsonIgnoreProperties({"password", "username", "name", "email", "phone", "boards", "roles"})
    private User user;
    private Integer viewCnt;
    private Integer replyCnt;
    private List<Reply> replyList;
    private List<Image> imageList;
}
