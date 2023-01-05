package com.example.thingsFlow.controller;

import com.example.thingsFlow.dto.InsertDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping(value = "/api/test")
    public Integer test(@RequestParam("seq") int seq) {
        System.out.println(seq);
        return seq;
    }

    @PostMapping(value = "/api/insert")
    public InsertDTO insert(@RequestBody InsertDTO insertDTO) {
        System.out.println("insert 메서드 실행");
        Board board = Board.builder()
                .title(insertDTO.getTitle())
                .content(insertDTO.getContent())
                .password(insertDTO.getPassword())
                .build();

        boardRepository.save(board);
        return insertDTO;
    }
}
