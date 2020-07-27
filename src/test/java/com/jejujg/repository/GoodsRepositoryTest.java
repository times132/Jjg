package com.jejujg.repository;

import com.jejujg.model.Goods;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GoodsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(GoodsRepositoryTest.class);

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void 게시물_조회(){
        Goods goods = goodsRepository.findById(1L).get();

        assertEquals(1L, goods.getBid());
        logger.info("게시물 정보: " + goods.getContent());
    }

//    @Test
//    public void 게시물_작성(){
//        Goods board = Goods.builder()
//                .title("테스트1")
//                .category("에어컨")
//                .content("에어컨 12평")
//                .writer("관리자")
//                .price(1000000)
//                .build();
//
//       Goods savedBoard = goodsRepository.save(board);
//       assertThat(savedBoard.getBid(), is(notNullValue()));
//    }
//
//    @Test
//    public void 게시물_수정(){
//        Goods board = Goods.builder()
//                .bid(1L)
//                .title("테스트1 수정")
//                .category("에어컨")
//                .content("에어컨 12평")
//                .writer("관리자")
//                .price(1000000)
//                .build();
//
//        Goods savedBoard = goodsRepository.save(board);
//        assertThat(savedBoard.getTitle(), is("테스트1 수정"));
//    }

    @Test
    public void 게시물_삭제(){
        goodsRepository.deleteById(1L);
        assertThat(Optional.empty(), is(goodsRepository.findById(1L)));
    }
}