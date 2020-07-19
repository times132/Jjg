package com.jejujg.service;

import com.jejujg.model.Board;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequest request){
        return boardRepository.save(request.toEntity()).getBid();
    }

    @Transactional
    public BoardResponse findOne(Long bid){
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        return new BoardResponse().toDto(board);
    }

    @Transactional
    public Long update(Long bid, BoardRequest request){
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        board.update(request);

        return bid;
    }
}
