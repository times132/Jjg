package com.jejujg.service;

import com.jejujg.common.Criteria;
import com.jejujg.mapper.GoodsMapper;
import com.jejujg.model.CategoryItem;
import com.jejujg.model.Goods;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsMapper mapper;

    public Page<Goods> list(Criteria criteria, CategoryItem categoryItem){
        Pageable pageable = PageRequest.of(criteria.getPage() - 1, criteria.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<Goods> page;

        switch (criteria.getType()){
            // 제목
            case "T": page = goodsRepository.findAllByTitleContaining(criteria.getKeyword(), pageable); break;
            // 내용
            case "C": page = goodsRepository.findAllByContentContaining(criteria.getKeyword(), pageable); break;
            // 카테고리
//            case "I": page = goodsRepository.findAllByCategory(criteria.getKeyword(), pageable); break;
            // 작성자
            case "W": page = goodsRepository.findAllByWriter(criteria.getKeyword(), pageable); break;
            default: page = goodsRepository.findAllByCategoryItem(categoryItem, pageable); break;
        }

        return page;
    }

    @Transactional
    public Long save(GoodsRequest request){
        return goodsRepository.save(mapper.goodsDtoToEntity(request)).getBid();

    }

    @Transactional
    public GoodsResponse findOne(Long bid){
        Goods goods = goodsRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        return mapper.goodsEntityToDTO(goods);
    }

    @Transactional
    public Long update(Long bid, GoodsRequest request){
        Goods goods = goodsRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));

        goods.update(request);

        return bid;
    }

    @Transactional
    public Long delete(Long bid){
        if (goodsRepository.existsById(bid)){
            goodsRepository.deleteById(bid);
            return bid;
        } else {
            throw new IllegalArgumentException("존재 하지 않는 게시물 입니다.");
        }
    }
}
