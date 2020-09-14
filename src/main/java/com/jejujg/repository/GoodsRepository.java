package com.jejujg.repository;

import com.jejujg.model.CategoryItem;
import com.jejujg.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @EntityGraph(attributePaths = "image")
    @Query(value = "select DISTINCT g from Goods g where g.categoryItem=?1")
    Page<Goods> findAllByCategoryItem(CategoryItem categoryItem, Pageable pageable);
    Page<Goods> findAllByTitleContaining(String title, Pageable pageable);
    Page<Goods> findAllByContentContaining(String content, Pageable pageable);
    Page<Goods> findAllByWriter(String writer, Pageable pageable);

    @EntityGraph(attributePaths = "image")
    @Query(value = "select DISTINCT g from Goods g where g.gid=?1")
    Optional<Goods> findByGid(Long gid);
}
