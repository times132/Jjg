package com.jejujg.Board;

import com.jejujg.Board.helper.BoardTestHelper;
import com.jejujg.User.helper.DiffRoleUserSetting;
import com.jejujg.model.Board;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardServiceTest extends DiffRoleUserSetting {

    @MockBean
    private BoardService boardService;

    private BoardTestHelper boardTestHelper;

    @BeforeEach
    void before() {
        prepareTwoUser();
    }

    @DisplayName("1. 게시물 작성")
    @Test
    void test_1() {
        BoardRequest request = boardTestHelper.makeBoard(USER1, "테스트 제목", "테스트 내용", "011").build();

        Board board = boardService.save(request);

        assertEquals()
    }
}
