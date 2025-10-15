package com.zzapoong.comment.service;

import com.zzapoong.comment.repository.CommentRepository;
import com.zzapoong.comment.dto.CommentCreateDTO;
import com.zzapoong.comment.dto.CommentCreateResponseDTO;
import com.zzapoong.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentCreateResponseDTO createComment(CommentCreateDTO request){
        String comment = request.getComment();
        String writer = request.getWriter();
        long boardId = request.getBoardId();

        System.out.println("createComment 호출됨");

        // 현재 시간 구하기
        LocalDateTime now  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = now.format(formatter);

        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .writer(writer)
                .boardId(boardId)
                .createdAt(formatted)
                .build();

        CommentEntity save = commentRepository.save(commentEntity);
        CommentCreateResponseDTO build = CommentCreateResponseDTO.builder().id(save.getId()).build();

        return CommentCreateResponseDTO.builder().id(save.getId()).message("생성 성공").build();
    }

    public CommentEntity updateComment(Long id, String newComment) {
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 댓글이 존재하지 않습니다."));

        comment.setComment(newComment);

        return commentRepository.save(comment);
    }


    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다.");
        }
        commentRepository.deleteById(id);
    }
}
