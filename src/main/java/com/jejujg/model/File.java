package com.jejujg.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class File extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String uuid;

    private String path;

    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public File(Long fid, String uuid, String path, String fileName, Board board){
        this.fid = fid;
        this.uuid = uuid;
        this.path = path;
        this.fileName = fileName;
        this.board = board;
    }
}
