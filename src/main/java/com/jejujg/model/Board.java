package com.jejujg.model;

import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.request.GoodsRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
    private String title;
    @Lob
    private String content;
    private Integer price;
    private String writer;
    private Integer viewCnt;
    private Integer replyCnt;
    private Long groupNum;
    private Integer groupOrder;
    private Integer depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryItem_id")
    private CategoryItem categoryItem;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList;

//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Image> imageList;

    @PrePersist
    protected void prePersist() {
        if (this.viewCnt == null) this.viewCnt = 0;
        if (this.replyCnt == null) this.replyCnt = 0;
    }

    @PostPersist
    private void postPersist() {
        if (this.groupNum == null) {
            this.groupNum = this.bid;
            this.groupOrder = 1;
        }
    }

    public void update(BoardRequest request, List<Image> imageList){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.categoryItem = request.getCategoryItem();
//        this.imageList = request.getImageList();
    }
}
