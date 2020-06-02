package com.jejujg.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

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

    @Builder
    public Board(String category, String title, String content, Integer price, String writer){
        this.category = category;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
    }
}
