package com.jejujg.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private UserRole name;

    @Builder
    public Role(Long id, UserRole name){
        this.id = id;
        this.name = name;
    }
}
