package com.jejujg.mapper;

import com.jejujg.model.File;
import com.jejujg.model.Goods;
import com.jejujg.model.Goods.GoodsBuilder;
import com.jejujg.payload.request.GoodsRequest;
import com.jejujg.payload.response.GoodsResponse;
import com.jejujg.payload.response.GoodsResponse.GoodsResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-15T22:46:30+0900",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class GoodsMapperImpl implements GoodsMapper {

    @Override
    public Goods goodsDtoToEntity(GoodsRequest request) {
        if ( request == null ) {
            return null;
        }

        GoodsBuilder goods = Goods.builder();

        goods.title( request.getTitle() );
        goods.content( request.getContent() );
        goods.price( request.getPrice() );
        goods.writer( request.getWriter() );
        List<File> list = request.getFiles();
        if ( list != null ) {
            goods.files( new ArrayList<File>( list ) );
        }
        goods.categoryItem( request.getCategoryItem() );

        return goods.build();
    }

    @Override
    public GoodsResponse goodsEntityToDTO(Goods goods) {
        if ( goods == null ) {
            return null;
        }

        GoodsResponseBuilder goodsResponse = GoodsResponse.builder();

        goodsResponse.bid( goods.getBid() );
        goodsResponse.title( goods.getTitle() );
        goodsResponse.content( goods.getContent() );
        goodsResponse.price( goods.getPrice() );
        goodsResponse.writer( goods.getWriter() );
        List<File> list = goods.getFiles();
        if ( list != null ) {
            goodsResponse.files( new ArrayList<File>( list ) );
        }

        return goodsResponse.build();
    }
}
