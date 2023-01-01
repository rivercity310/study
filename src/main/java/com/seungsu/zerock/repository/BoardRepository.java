package com.seungsu.zerock.repository;

import com.seungsu.zerock.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {
    List<BoardVO> getBoardList();
    BoardVO getBoard(Long bno);
    void insertBoard(BoardVO boardVO);
    void updateBoard(BoardVO boardVO);
    void deleteBoard(Long bno);
}
