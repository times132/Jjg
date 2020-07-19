package com.jejujg.mapper;

import com.jejujg.model.Board;
import com.jejujg.payload.request.BoardRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    Board boardDtoToEntity(BoardRequest request);
}
