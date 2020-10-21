package com.jejujg.service;

import com.jejujg.model.Category;
import com.jejujg.model.CategoryItem;
import com.jejujg.repository.CategoryItemRepository;
import com.jejujg.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    public CategoryItem findOne(String itemNum){
        CategoryItem categoryItem = categoryItemRepository.findByItemNum(itemNum)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 카테고리입니다."));

        return categoryItem;
    }

    public void findAllGoods(String categoryName){
        Category category = categoryRepository.findAllByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 카테고리입니다."));
        List<CategoryItem> bb = category.getCategories();
    }
}
