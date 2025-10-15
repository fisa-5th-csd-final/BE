package com.zzapoong.board.service;

import com.zzapoong.board.dto.BoardCreateDTO;
import com.zzapoong.board.dto.BoardResponseDTO;
import com.zzapoong.board.dto.BoardsResponseDTO;
import com.zzapoong.board.entity.Board;
import com.zzapoong.board.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    @Transactional
    public Long create(BoardCreateDTO request){
        Board board = createOne(request);

        return repository.save(board).getId();
    }

    @Transactional
    public void delete(Long boardId){
        repository.deleteById(boardId);
    }

    public BoardResponseDTO get(Long boardId){
        Board board = repository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Board"));

        return BoardResponseDTO.create(board);
    }

    public BoardsResponseDTO getPage(int page, int size) {
        // PageRequest: 페이지 번호, 크기, 정렬 방식 지정
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // repository에서 페이징된 결과 조회
        Page<Board> boardPage = repository.findAll(pageable);

        // Board 엔티티 → DTO 변환
        List<BoardResponseDTO> boardResponses = boardPage
                .getContent() // 현재 페이지의 데이터만 가져옴
                .stream()
                .map(BoardResponseDTO::create)
                .toList();

        // DTO에 전체 페이지 정보도 함께 담아서 반환
        return BoardsResponseDTO.create(
                boardResponses,
                boardPage.getTotalPages(),
                size,
                page
        );
    }


    private Board createOne(BoardCreateDTO request){
        return Board.builder()
                .title(request.getTitle())
                .contents(request.getContent())
                .writer(request.getWriter())
                .build();
    }

}
