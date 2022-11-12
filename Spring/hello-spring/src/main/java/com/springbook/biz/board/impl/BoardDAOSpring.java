package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAOSpring {
    private JdbcTemplate jdbcTemplate;

    private final String BOARD_INSERT =
            "INSERT INTO BOARD(seq, title, writer, content) values((select nvl (max(seq), 0) + 1 from board), ?, ?, ?)";
    private final String BOARD_UPDATE =
            "UPDATE BOARD SET title=?, content=? WHERE seq=?";
    private final String BOARD_DELETE =
            "DELETE BOARD WHERE seq=?";
    private final String BOARD_GET =
            "SELECT * FROM BOARD WHERE seq=?";
    private final String BOARD_LIST =
            "SELECT * FROM BOARD ORDER BY seq DESC";

    @Autowired
    public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
    */

    // 글 등록
    public void insertBoard(BoardVO vo) {
        System.out.println("[Spring JDBC] insertBoard() 실행");
        jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        System.out.println("[Spring JDBC] updateBoard() 실행");
        jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("[Spring JDBC] deleteBoard() 실행");
        jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("[Spring JDBC] getBoard() 실행");
        Object[] args = {vo.getSeq()};

        return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("[Spring JDBC] getBoardList() 실행");
        return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
    }
}

class BoardRowMapper implements RowMapper<BoardVO> {
    @Override
    public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BoardVO board = new BoardVO();
        board.setSeq(rs.getInt("SEQ"));
        board.setTitle(rs.getString("TITLE"));
        board.setWriter(rs.getString("WRITER"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDate(rs.getDate("REGDATE"));
        board.setCnt(rs.getInt("cnt"));

        return board;
    }
}
