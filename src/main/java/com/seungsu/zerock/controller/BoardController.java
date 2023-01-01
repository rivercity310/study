package com.seungsu.zerock.controller;

import com.seungsu.zerock.domain.BoardVO;
import com.seungsu.zerock.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    @Autowired
    private final BoardService boardService;

    @PostMapping("board/insert")
    public void insertBoard(BoardVO board) {
        boardService.insertBoard(board);
    }

    @GetMapping("board/delete")
    public void deleteBoard(Long bno) {
        boardService.deleteBoard(bno);
    }

    @PutMapping("board/update")
    public void updateBoard(BoardVO board) {
        boardService.updateBoard(board);
    }

    @GetMapping("board/get")
    public BoardVO getBoard(Long bno) {
        return boardService.getBoard(bno);
    }

    @GetMapping("board/getList")
    public List<BoardVO> getBoardList() {
        return boardService.getBoardList();
    }
}
