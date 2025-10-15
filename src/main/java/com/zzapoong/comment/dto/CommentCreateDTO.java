package com.zzapoong.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDTO {
    private long boardId;
    private String writer;
    private String comment;
}
