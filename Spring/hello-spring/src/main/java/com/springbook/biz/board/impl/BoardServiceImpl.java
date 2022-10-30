package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    public void insertBoard(BoardVO vo) throws SQLException {
        boardDAO.insertBoard(vo);
    }

    public void updateBoard(BoardVO vo) throws SQLException {
        boardDAO.updateBoard(vo);
    }

    public void deleteBoard(BoardVO vo) throws SQLException {
        boardDAO.deleteBoard(vo);
    }

    public BoardVO getBoard(BoardVO vo) throws SQLException {
        return boardDAO.getBoard(vo);
    }

    public List<BoardVO> getBoardList(BoardVO vo) throws SQLException {
        return boardDAO.getBoardList(vo);
    }
}
