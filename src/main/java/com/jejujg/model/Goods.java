package com.jejujg.model;

import com.jejujg.payload.request.GoodsRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Goods extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    private String title;
    @Lob
    private String content;
    private String writer;
    private Integer price;
    private Integer viewCnt;
    private Integer replyCnt;
    private Long groupNum;
    private Integer groupOrder;
    private Integer depth;
    private String thumbImageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryItem_id")
    private CategoryItem categoryItem;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageList;

    public void update(GoodsRequest request, Image image){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.categoryItem = request.getCategoryItem();
    }
}
