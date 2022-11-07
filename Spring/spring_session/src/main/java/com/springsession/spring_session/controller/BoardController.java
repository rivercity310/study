package com.springsession.spring_session.controller;

import com.springsession.spring_session.entity.Board;
import com.springsession.spring_session.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/write")
    public String main() {
        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {
        boardService.write(board);
        return "";
    }
}
