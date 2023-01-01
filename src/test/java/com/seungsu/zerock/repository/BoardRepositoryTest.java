package com.seungsu.zerock.repository;

import com.seungsu.zerock.domain.BoardVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardRepositoryTest {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    @DisplayName("getBoardList 테스트")
    public void getBoardListTest() {
        List<BoardVO> boardList = boardRepository.getBoardList();
        System.out.println(boardList);
    }

    @Test
    @DisplayName("getBoard 테스트")
    public void getBoardTest() {
        BoardVO board = boardRepository.getBoard(1L);
        Assertions.assertEquals(1, board.getBno());
    }

    @Test
    @DisplayName("insertBoard 테스트")
    public void insertBoardTest() {
        BoardVO board = new BoardVO();
        board.setTitle("테스트222");
        board.setContent("테스트222 Content");
        board.setWriter("User22");

        boardRepository.insertBoard(board);
        Assertions.assertEquals("테스트222", boardRepository.getBoard(4L).getTitle());
    }

    @Test
    @DisplayName("deleteBoard 테스트")
    public void deleteBoardTest() {
        boardRepository.deleteBoard(4L);
    }

    @Test
    @DisplayName("updateBoard 테스트")
    public void updateBoardTest() {
        BoardVO board = new BoardVO();
        board.setBno(2L);
        board.setTitle("업데이트 테스트 제목");
        board.setContent("업데이트 테스트 콘텐츠");
        board.setWriter("황승수");

        boardRepository.updateBoard(board);
        Assertions.assertEquals("황승수", boardRepository.getBoard(2L).getWriter());
    }
}
