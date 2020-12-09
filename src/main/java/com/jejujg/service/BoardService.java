package com.jejujg.service;

import com.jejujg.common.Criteria;
import com.jejujg.mapper.BoardMapper;
import com.jejujg.model.Board;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Image;
import com.jejujg.payload.dto.BoardList;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardResponse;
import com.jejujg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper mapper;

    public Page<BoardList> list(Criteria criteria, CategoryItem categoryItem){
        Pageable pageable = PageRequest.of(criteria.getPage() - 1, criteria.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<Board> page;

        switch (criteria.getType()){
            case "T": page = boardRepository.findAllByTitleContaining(criteria.getKeyword(), pageable); break; // 제목
            case "C": page = boardRepository.findAllByContentContaining(criteria.getKeyword(), pageable); break; // 내용
//            case "I": page = goodsRepository.findAllByCategory(criteria.getKeyword(), pageable); break; // 카테고리
            case "W": page = boardRepository.findAllByWriter(criteria.getKeyword(), pageable); break; // 작성자
            default: page = boardRepository.findAllByCategoryItem(categoryItem, pageable); break;
        }

        return new PageImpl<>(mapper.boardEntityToListDTO(page.getContent()), pageable, page.getTotalElements());
    }

    @Transactional
    public Board save(BoardRequest request, Image image){

        return boardRepository.save(mapper.boardDtoToEntity(request, image));
    }

    @Transactional
    public BoardResponse findOne(Long bid){
        Board board = boardRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        return mapper.boardEntityToDTO(board);
    }

    @Transactional
    public Long update(Long bid, BoardRequest request, Image image){
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        board.update(request, image);

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
    public Image findImage(Long bid){
        Image image = boardRepository.findByBid(bid).get().getImage();

        return image;
    }
}
