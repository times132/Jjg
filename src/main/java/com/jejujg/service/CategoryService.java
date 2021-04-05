package com.jejujg.service;

import com.jejujg.model.Goods;
import com.jejujg.model.Category;
import com.jejujg.model.CategoryItem;
import com.jejujg.repository.CategoryItemRepository;
import com.jejujg.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    public CategoryItem findSubCategory(String itemNum){
        CategoryItem categoryItem = categoryItemRepository.findByItemNum(itemNum)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 카테고리입니다."));

        return categoryItem;
    }

    public ArrayList<Goods> findMainCategory(Long categoryNum){
        ArrayList<Goods> goods = new ArrayList<>();

        Category category = categoryRepository.findAllById(categoryNum)
                .orElseThrow(() -> new IllegalArgumentException("없는 카테고리입니다."));

        for(CategoryItem c : category.getCategoryItems()) {
            goods.addAll(c.getGoods());
        }

        return goods;
    }
}
