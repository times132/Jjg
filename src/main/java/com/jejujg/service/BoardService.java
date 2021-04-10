package com.jejujg.service;

import com.jejujg.common.Criteria;
import com.jejujg.mapper.BoardMapper;
import com.jejujg.mapper.GoodsMapper;
import com.jejujg.model.Board;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import com.jejujg.model.User;
import com.jejujg.payload.dto.BoardList;
import com.jejujg.payload.dto.GoodsList;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.repository.BoardRepository;
import com.jejujg.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public Page<BoardList> list(Criteria criteria, CategoryItem categoryItem){
        Pageable pageable = PageRequest.of(criteria.getPage() - 1, criteria.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<Board> page;

        switch (criteria.getType()){
            case "T": page = boardRepository.findAllByTitleContaining(criteria.getKeyword(), pageable); break; // 제목
            case "C": page = boardRepository.findAllByContentContaining(criteria.getKeyword(), pageable); break; // 내용
            case "W": page = boardRepository.findAllByWriter(criteria.getKeyword(), pageable); break; // 작성자
            default: page = boardRepository.findAllByCategoryItem(categoryItem, pageable); break;
        }

        return new PageImpl<>(boardMapper.boardEntityToListDto(page.getContent()), pageable, page.getTotalElements());
    }

    @Transactional
    public Board save(BoardRequest request, User user){
        return boardRepository.save(boardMapper.boardDtoToEntity(request, user));
    }

    @Transactional
    public BoardResponse findOne(Long bid){
        Board board = boardRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        return boardMapper.boardEntityToDto(board);
    }

    @Transactional
    public Long update(Long bid, BoardRequest request, List<Image> imageList){
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        board.update(request, imageList);

        return bid;
    }

    @Transactional
    public Long delete(Long bid){
        if (boardRepository.existsById(bid)){
            boardRepository.deleteById(bid);
            return bid;
        } else {
            throw new IllegalArgumentException("존재 하지 않는 게시물 입니다.");
        }
    }

    @Transactional
    public List<Image> findImage(Long bid){
        return boardRepository.findByBid(bid).get().getImageList();
    }
}
