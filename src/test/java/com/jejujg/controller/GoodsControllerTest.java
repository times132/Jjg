package com.jejujg.controller;

import com.jejujg.security.JwtAuthenticationEntryPoint;
import com.jejujg.security.JwtTokenProvider;
import com.jejujg.service.GoodsService;
import com.jejujg.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class GoodsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private GoodsService goodsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    public void 게시물_조회() throws Exception{
        ResultActions actions = mvc.perform(get("/board/4")
            .contentType(MediaType.APPLICATION_JSON)).andDo(print());

//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("bid").value(1L))
//                .andExpect(jsonPath("title").value("테스트1"))
//                .andExpect(jsonPath("price").value(1000000));
    }
}
