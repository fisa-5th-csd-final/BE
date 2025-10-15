package com.zzapoong.board.controller;

import com.zzapoong.board.dto.BoardCreateDTO;
import com.zzapoong.board.dto.BoardResponseDTO;
import com.zzapoong.board.dto.BoardsResponseDTO;
import com.zzapoong.board.entity.Board;
import com.zzapoong.board.service.BoardService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createBoard(@RequestBody BoardCreateDTO request){
        Long savedId = boardService.create(request);

        return ResponseEntity.ok(Map.of("id", savedId));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> get(@PathVariable("boardId") Long boardId){
        return ResponseEntity.ok(boardService.get(boardId));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("boardId") Long boardId){
        boardService.delete(boardId);

        return ResponseEntity.ok(Map.of("message", "삭제 성공"));
    }

    @GetMapping
    public ResponseEntity<BoardsResponseDTO> getPage(
                @RequestParam("page") int page,
                @RequestParam("size") int size){
        // 항상 생성 시간 순 정렬
        BoardsResponseDTO response = boardService.getPage(page, size);

        return ResponseEntity.ok(response);
    }

}
