package com.jejujg.repository;

import com.jejujg.model.CategoryItem;
import com.jejujg.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Page<Goods> findAllByCategoryItem(CategoryItem categoryItem, Pageable pageable);
    Page<Goods> findAllByTitleContaining(String title, Pageable pageable);
    Page<Goods> findAllByContentContaining(String content, Pageable pageable);
    Page<Goods> findAllByWriter(String writer, Pageable pageable);

}
