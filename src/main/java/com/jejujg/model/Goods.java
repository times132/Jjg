package com.jejujg.model;

import com.jejujg.payload.request.GoodsRequest;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Goods extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

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

    public void update(GoodsRequest request, Image image){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.image = image;
        this.categoryItem = request.getCategoryItem();
    }
}
