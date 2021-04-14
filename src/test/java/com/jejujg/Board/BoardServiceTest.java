package com.jejujg.Board;

import com.jejujg.Board.helper.BoardTestHelper;
import com.jejujg.User.helper.DiffRoleUserSetting;
import com.jejujg.common.Criteria;
import com.jejujg.model.Board;
import com.jejujg.payload.dto.BoardList;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.repository.BoardRepository;
import com.jejujg.service.BoardService;
import com.jejujg.service.CategoryService;
import com.jejujg.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardServiceTest extends DiffRoleUserSetting {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CategoryService categoryService;

    protected BoardTestHelper boardTestHelper;

    @BeforeEach
    void before() {
        prepareTwoUser();
        boardTestHelper = new BoardTestHelper(boardRepository, categoryService);
    }

    @DisplayName("1. 게시물 작성")
    @Test
    void test_1() {
        BoardRequest request = boardTestHelper.makeBoardRequest(USER1, "테스트 제목", "테스트 내용", "011").build();

        Board board = boardService.save(request, USER1);

        assertEquals("테스트 제목", board.getTitle());
    }

    @DisplayName("2. 게시물 조회")
    @Test
    void test_2() {
        BoardResponse response = boardService.findOne(1L);

        assertEquals(1L, response.getBid());
        assertEquals("테스트 제목", response.getTitle());

        assertThrows(IllegalArgumentException.class, () -> boardService.findOne(5L));
    }

    @DisplayName("3. 게시물 페이지 별 조회")
    @Test
    void test_3() {
        Criteria criteria = new Criteria();
        Page<BoardList> boardList = boardService.list(criteria, boardTestHelper.getCategoryItem("011"));
    }

    @DisplayName("4. 게시물 수정")
    @Test
    void test_4() {

    }

    @DisplayName("5. 게시물 삭제")
    @Test
    void test_5() {

    }

    @DisplayName("6. 하위 게시물 작성")
    @Test
    void test_6() {

    }
}
