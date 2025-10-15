package com.zzapoong.comment.controller;

import com.zzapoong.comment.dto.CommentCreateDTO;
import com.zzapoong.comment.dto.CommentCreateResponseDTO;
import com.zzapoong.comment.entity.CommentEntity;
import com.zzapoong.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentCreateResponseDTO> createComment(@RequestBody CommentCreateDTO request){
        System.out.println("createComment 실행됨");
        CommentCreateResponseDTO comment = commentService.createComment(request);
//        System.out.println(comment.getMessage());
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", comment.getId());
//        response.put("message", "생성 완료");

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "삭제 성공");
        response.put("id", id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentEntity> updateComment(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        String newComment = request.get("comment");
        CommentEntity updated = commentService.updateComment(id, newComment);
        return ResponseEntity.ok(updated);
    }

}
