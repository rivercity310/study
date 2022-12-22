package com.example.assignment1.board;

import java.util.List;

public interface BoardService {
    public void insertBoard(BoardVO vo);
    public void deleteBoard(BoardVO vo);
    public void updateBoard(BoardVO vo);
    public BoardVO getBoard(BoardVO vo);
    public List<BoardVO> getBoardContentList(BoardVO vo);
    public List<BoardVO> getBoardList(BoardVO vo);
}
