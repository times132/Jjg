package com.jejujg.repository;


import com.jejujg.model.Category;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryTest {

    private static final Logger logger = LoggerFactory.getLogger(GoodsRepositoryTest.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 카테고리제품(){
        Category category = categoryRepository.findAllByName("에어컨")
                .orElseThrow(() -> new IllegalArgumentException("없는 카테고리입니다."));
        logger.info("카테고리: " + category);
    }
}
