package com.jejujg.model;

import com.jejujg.payload.request.BoardRequest;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Board extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @Column(length = 40)
    private String title;

    @Lob
    private String content;

    @Column(length = 10)
    private Integer price;

    @Column(length = 10)
    private String writer;

    @Nullable
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategotyItem_id")
    private CategoryItem categoryItem;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList;

    public void update(BoardRequest request, Image image){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.image = image;
        this.categoryItem = request.getCategoryItem();
    }
}
