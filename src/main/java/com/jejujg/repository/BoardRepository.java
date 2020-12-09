package com.jejujg.repository;

import com.jejujg.model.Board;
import com.jejujg.model.CategoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // image 필드를 Eager로 조회한다.
    @EntityGraph(attributePaths = "image")
    @Query(value = "select DISTINCT b from Board b where b.categoryItem=?1")
    Page<Board> findAllByCategoryItem(CategoryItem categoryItem, Pageable pageable);
    Page<Board> findAllByTitleContaining(String title, Pageable pageable);
    Page<Board> findAllByContentContaining(String content, Pageable pageable);
    Page<Board> findAllByWriter(String writer, Pageable pageable);

    @EntityGraph(attributePaths = {"image", "categoryItem"})
    @Query(value = "select DISTINCT b from Board b where b.bid=?1")
    Optional<Board> findByBid(Long bid);
//    Image findImageByGid(Long gid);
//    @Query(value = "select g from Goods g where g.categoryItem=?1 or g.categoryItem=?2 or g.categoryItem=?3")
//    Page<Goods> findAllByMainCategoryItem()
}
