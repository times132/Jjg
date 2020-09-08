package com.jejujg.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GoodsList {

    private Long gid;
    private String title;
}
