package com.jejujg.mapper;

import com.jejujg.model.Goods;
import com.jejujg.model.Goods.GoodsBuilder;
import com.jejujg.model.Image;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsListResponse;
import com.jejujg.payload.response.GoodsListResponse.GoodsListResponseBuilder;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.payload.response.GoodsResponse.GoodsResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-03T17:41:07+0900",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class GoodsMapperImpl implements GoodsMapper {

    @Override
    public Goods goodsDtoToEntity(GoodsRequest request, Image image) {
        if ( request == null && image == null ) {
            return null;
        }

        GoodsBuilder goods = Goods.builder();

        if ( request != null ) {
            goods.title( request.getTitle() );
            goods.content( request.getContent() );
            goods.price( request.getPrice() );
            goods.writer( request.getWriter() );
            goods.categoryItem( request.getCategoryItem() );
        }
        if ( image != null ) {
            goods.image( image );
        }

        return goods.build();
    }

    @Override
    public GoodsResponse goodsEntityToDTO(Goods goods) {
        if ( goods == null ) {
            return null;
        }

        GoodsResponseBuilder goodsResponse = GoodsResponse.builder();

        goodsResponse.gid( goods.getGid() );
        goodsResponse.title( goods.getTitle() );
        goodsResponse.content( goods.getContent() );
        goodsResponse.price( goods.getPrice() );
        goodsResponse.writer( goods.getWriter() );
        goodsResponse.image( goods.getImage() );

        return goodsResponse.build();
    }

    @Override
    public List<GoodsListResponse> goodsEntityToListDTO(List<Goods> goodsList) {
        if ( goodsList == null ) {
            return null;
        }

        List<GoodsListResponse> list = new ArrayList<GoodsListResponse>( goodsList.size() );
        for ( Goods goods : goodsList ) {
            list.add( goodsToGoodsListResponse( goods ) );
        }

        return list;
    }

    protected GoodsListResponse goodsToGoodsListResponse(Goods goods) {
        if ( goods == null ) {
            return null;
        }

        GoodsListResponseBuilder goodsListResponse = GoodsListResponse.builder();

        goodsListResponse.gid( goods.getGid() );
        goodsListResponse.title( goods.getTitle() );

        return goodsListResponse.build();
    }
}
