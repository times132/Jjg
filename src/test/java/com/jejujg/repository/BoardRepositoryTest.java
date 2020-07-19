package com.jejujg.repository;

import com.jejujg.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryTest.class);

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 게시물_조회(){
        Board board = boardRepository.findById(1L).get();

        assertEquals(1L, board.getBid());
        logger.info("게시물 정보: " + board.getContent());
    }

    @Test
    public void 게시물_작성(){
        Board board = Board.builder()
                .title("테스트1")
                .category("에어컨")
                .content("에어컨 12평")
                .writer("관리자")
                .price(1000000)
                .build();

       Board savedBoard = boardRepository.save(board);
       assertThat(savedBoard.getBid(), is(notNullValue()));
    }

    @Test
    public void 게시물_수정(){
        Board board = Board.builder()
                .bid(1L)
                .title("테스트1 수정")
                .category("에어컨")
                .content("에어컨 12평")
                .writer("관리자")
                .price(1000000)
                .build();

        Board savedBoard = boardRepository.save(board);
        assertThat(savedBoard.getTitle(), is("테스트1 수정"));
    }

    @Test
    public void 게시물_삭제(){
        boardRepository.deleteById(1L);
        assertThat(Optional.empty(), is(boardRepository.findById(1L)));
    }
}
