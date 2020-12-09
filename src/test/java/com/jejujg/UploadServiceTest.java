package com.jejujg;

import com.jejujg.helper.UploadTestHelper;
import com.jejujg.model.Image;
import com.jejujg.service.UploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadServiceTest extends UploadTestHelper {

    @Autowired
    private UploadService uploadService;

    @BeforeEach
    void before() throws IOException {

    }

    @DisplayName("1. 이미지 파일 DB에 저장")
    @Test
    void test_1() {

    }

    @DisplayName("2. 이미지 파일이면 썸네일, 원본 생성")
    @Test
    void test_2() {

    }

    @DisplayName("3. 이미지 파일이 아니면 오류")
    @Test
    void test_3() {

    }

    @DisplayName("4. 이미지 수정")
    @Test
    void test_4() {

    }

    @DisplayName("5. 이미지 삭제")
    @Test
    void test_5() {

    }
}
