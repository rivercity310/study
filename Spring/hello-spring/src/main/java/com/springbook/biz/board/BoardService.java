package com.springbook.biz.board;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    void insertBoard(BoardVO vo) throws SQLException;
    void updateBoard(BoardVO vo) throws SQLException;
    void deleteBoard(BoardVO vo) throws SQLException;
    BoardVO getBoard(BoardVO vo) throws SQLException;
    List<BoardVO> getBoardList(BoardVO vo) throws SQLException;
}
