package com.jejujg.payload.dto;

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
public class GoodsList {

    private Long gid;
    private String title;
    private String thumbImageName;
}
