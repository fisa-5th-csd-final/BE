package com.zzapoong.board.dto;

import com.zzapoong.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardResponseDTO {

    private String title;
    private String content;
    private String writer;
    private String createdAt;

    //TODO: comment 리스트 응답 추가

    public static BoardResponseDTO create(Board board){
        return new BoardResponseDTO(
                board.getTitle(),
                board.getContents(),
                board.getWriter(),
                board.getCreatedAt().toString()
                // TODO: comment 리스트 응답 추가
        );
    }

}
