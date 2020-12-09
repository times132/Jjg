package com.jejujg;

import com.jejujg.controller.UploadController;
import com.jejujg.helper.DiffRoleUserIntegrationTest;
import com.jejujg.security.JwtUtil;
import com.jejujg.service.CookieUtil;
import com.jejujg.service.RedisUtil;
import com.jejujg.service.UploadService;
import com.jejujg.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadControllerTest extends DiffRoleUserIntegrationTest {

//    @MockBean
//    private UploadService uploadService;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private JwtUtil jwtUtil;
//
//    @MockBean
//    private CookieUtil cookieUtil;
//
//    @MockBean
//    private RedisUtil redisUtil;

    @BeforeEach
    void before() {
        prepareTwoUser();
    }

    @DisplayName("1. 게스트는 파일 업로드 불가")
    @Test
    void test_1() throws URISyntaxException {
        String accessToken = getToken("guest1", "1234").getAccessToken();
        System.out.println("accessToken: " + accessToken);
    }

    @DisplayName("2. 파일이 이미지 일때 업로드")
    @Test
    void test_2() throws IOException, URISyntaxException {
        String accessToken = getToken("user1", "1234").getAccessToken();
        System.out.println("accessToken: " + accessToken);

        FileInputStream fis = new FileInputStream("D:\\test\\image1.jpg");
        MockMultipartFile file = new MockMultipartFile("image", fis);


    }

    @DisplayName("3. 파일이 이미지가 아니면 실패")
    @Test
    void test_3() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\test\\text1.jpg");
        MockMultipartFile file = new MockMultipartFile("text1", fis);
    }
}
