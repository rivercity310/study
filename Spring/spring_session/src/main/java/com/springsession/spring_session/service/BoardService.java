package com.springsession.spring_session.service;

import com.springsession.spring_session.entity.Board;
import com.springsession.spring_session.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void write(Board board) {
        boardRepository.save(board);
    }
}
