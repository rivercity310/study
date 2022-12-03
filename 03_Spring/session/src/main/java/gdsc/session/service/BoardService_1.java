/*
package gdsc.session.service;


import gdsc.session.entity.Board_java;
import gdsc.session.repository.BoardRepository_java;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService_1 {
    private final BoardRepository_java boardRepository;

    @Autowired
    public BoardService_1(BoardRepository_java boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void write(Board_java board) {
        boardRepository.save(board);
    }

    public List<Board_java> boardList() {
        return boardRepository.findAll();
    }

    public Board_java boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 특정 게시물 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
*/
