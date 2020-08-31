package com.jejujg.model;

import com.jejujg.payload.request.GoodsRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Goods extends DateAudit{

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

    @OneToMany(mappedBy = "goods")
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategotyItem_id")
    private CategoryItem categoryItem;

    @Builder
    public Goods(Long bid, String title, String content, Integer price, String writer, List<Image> images, CategoryItem categoryItem){
        this.bid = bid;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.images = images;
        this.categoryItem = categoryItem;
    }

    public void update(GoodsRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.images = request.getImages();
        this.categoryItem = request.getCategoryItem();
    }
}
