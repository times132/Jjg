package com.jejujg.mapper;

import com.jejujg.model.Goods;
import com.jejujg.model.Image;
import com.jejujg.payload.dto.GoodsList;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsMapper {

    @Mapping(target = "gid", ignore = true)
    Goods goodsDtoToEntity(GoodsRequest request, List<Image> imageList);
    GoodsResponse goodsEntityToDTO(Goods goods);
    GoodsList goodsToGoodsList(Goods goods);
    List<GoodsList> goodsEntityToListDTO(List<Goods> goodsList);
}
