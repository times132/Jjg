package com.jejujg.mapper;

import com.jejujg.model.Board;
import com.jejujg.model.Image;
import com.jejujg.payload.dto.BoardList;
import com.jejujg.payload.request.BoardRequest;
import com.jejujg.payload.response.BoardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    @Mapping(target = "bid", ignore = true)
    @Mapping(source = "image", target = "image")
    Board boardDtoToEntity(BoardRequest request, Image image);

    BoardResponse boardEntityToDTO(Board board);
    List<BoardList> boardEntityToListDTO(List<Board> boardList);
}
