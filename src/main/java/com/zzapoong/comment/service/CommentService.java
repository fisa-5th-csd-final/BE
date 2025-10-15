package com.zzapoong.comment.service;

import com.zzapoong.comment.repository.CommentRepository;
import com.zzapoong.comment.dto.CommentCreateDTO;
import com.zzapoong.comment.dto.CommentCreateResponseDTO;
import com.zzapoong.comment.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentService {
    private static CommentRepository commentRepository;

    public CommentCreateResponseDTO createComment(CommentCreateDTO request){
        String comment = request.getComment();
        String writer = request.getWriter();
        long boardId = request.getBoardId();

        // 현재 시간 구하기
        LocalDateTime now  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = now.format(formatter);

        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .writer(writer)
                .boardId(boardId)
                .build();

        CommentEntity save = commentRepository.save(commentEntity);

        return CommentCreateResponseDTO.builder().id(save.getId()).build();
    }



}
