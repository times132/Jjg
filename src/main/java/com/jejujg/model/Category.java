package com.jejujg.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categories;

    @Builder
    public Category(Long id, String name, List<CategoryItem> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }
}
