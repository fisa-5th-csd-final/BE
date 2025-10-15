package com.zzapoong.board.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardsResponseDTO {

    private List<BoardResponseDTO> boards;
    private Long totalPage;
    private Long size;
    private Long currentPage;

    public static BoardsResponseDTO create(List<BoardResponseDTO> boards, int totalPage, int size, int currentPage){
        return new BoardsResponseDTO(boards, (long) totalPage, (long) size, (long) currentPage);
    }

}
