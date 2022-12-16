package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    private BoardDAO boardDAO;
    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public void insertBoard(BoardVO vo) {
        boardDAO.insertBoard(vo);       // 100번 글 등록 성공
        // boardDAO.insertBoard(vo);       // Exception 발생 지점
    }

    @Override
    public void updateBoard(BoardVO vo) { boardDAO.updateBoard(vo); }

    @Override
    public void deleteBoard(BoardVO vo) {
        boardDAO.deleteBoard(vo);
    }

    @Override
    public BoardVO getBoard(BoardVO vo) {
        return boardDAO.getBoard(vo);
    }

    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.getBoardList();
    }
}
