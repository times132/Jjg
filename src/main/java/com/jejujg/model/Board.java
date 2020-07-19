package com.jejujg.model;

import com.jejujg.payload.request.BoardRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Board extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer price;

    @Column
    private String writer;

    @OneToMany(mappedBy = "board")
    private List<File> files;

    @Builder
    public Board(Long bid, String category, String title, String content, Integer price, String writer, List<File> files){
        this.bid = bid;
        this.category = category;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.files = files;
    }

    public void update(BoardRequest request){
        this.category = request.getCategory();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.files = request.getFiles();
    }
}
