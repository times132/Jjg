package com.jejujg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@JsonIgnoreProperties({"goods", "category"})
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(length = 10)
    private String itemNum;

    @OneToMany(mappedBy = "categoryItem")
    private List<Goods> goods;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public CategoryItem(Long id, String name, List<Goods> goods, Category category){
        this.id = id;
        this.name = name;
        this.goods = goods;
        this.category = category;
    }
}
