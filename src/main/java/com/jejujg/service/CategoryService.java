package com.jejujg.service;

import com.jejujg.model.CategoryItem;
import com.jejujg.repository.CategoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {


    private final CategoryItemRepository categoryItemRepository;

    public CategoryItem findOne(String itemNum){
        CategoryItem categoryItem = categoryItemRepository.findByItemNum(itemNum)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 카테고리입니다."));

        return categoryItem;
    }
}
