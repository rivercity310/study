package com.seungsu.zerock.service.impl;

import com.seungsu.zerock.domain.BoardVO;
import com.seungsu.zerock.repository.BoardRepository;
import com.seungsu.zerock.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void insertBoard(BoardVO board) {
        boardRepository.insertBoard(board);
    }

    @Override
    public void deleteBoard(Long bno) {
        boardRepository.deleteBoard(bno);
    }

    @Override
    public void updateBoard(BoardVO board) {
        boardRepository.updateBoard(board);
    }

    @Override
    public BoardVO getBoard(Long bno) {
        return boardRepository.getBoard(bno);
    }

    @Override
    public List<BoardVO> getBoardList() {
        return boardRepository.getBoardList();
    }
}
