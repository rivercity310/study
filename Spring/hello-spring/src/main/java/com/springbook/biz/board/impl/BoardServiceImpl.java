package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.LogAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    private BoardDAO boardDAO;
    // private LogAdvice log;               OOP 방식으로는 핵심 관심(BoardServiceImpl)에서 횡단 관심(LogAdvice)를 완벽하게 분리할 수 없음

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
        // this.log = log;
    }

    public void insertBoard(BoardVO vo) {
        /*
        After-Throwing 어드바이스 테스트용 코드
        if (vo.getSeq() == 0)
            throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
        */

        boardDAO.insertBoard(vo);
    }

    public void updateBoard(BoardVO vo) {
        boardDAO.updateBoard(vo);
    }

    public void deleteBoard(BoardVO vo) {
        boardDAO.deleteBoard(vo);
    }

    public BoardVO getBoard(BoardVO vo) {
        return boardDAO.getBoard(vo);
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        return boardDAO.getBoardList(vo);
    }
}
