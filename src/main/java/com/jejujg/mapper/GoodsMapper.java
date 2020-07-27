package com.jejujg.mapper;

import com.jejujg.model.Goods;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoodsMapper {

    @Mapping(target = "bid", ignore = true)
    Goods goodsDtoToEntity(GoodsRequest request);

    GoodsResponse goodsEntityToDTO(Goods goods);
}
