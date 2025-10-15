package com.zzapoong.comment.controller;

import com.zzapoong.comment.dto.CommentCreateDTO;
import com.zzapoong.comment.dto.CommentCreateResponseDTO;
import com.zzapoong.comment.entity.CommentEntity;
import com.zzapoong.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/api/comments")
public class CommentController {

    private static CommentService commentService  = new CommentService();

    @GetMapping
    public ResponseEntity<CommentCreateResponseDTO> createComment(@RequestBody CommentCreateDTO request){

        CommentCreateResponseDTO comment = commentService.createComment(request);

//        Map<String, Object> response = new HashMap<>();
//        response.put("id", comment.getId());
//        response.put("message", "생성 완료");

        return ResponseEntity.ok(comment);
    }
}
