package com.zzapoong.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardCreateDTO {

    private String title;
    private String content;
    private String writer;

}
