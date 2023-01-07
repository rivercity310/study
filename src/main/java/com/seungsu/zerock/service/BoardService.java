package com.seungsu.zerock.service;

import com.seungsu.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardVO board);
    void deleteBoard(Long bno);
    void updateBoard(BoardVO board);
    BoardVO getBoard(Long bno);
    List<BoardVO> getBoardList();
}
