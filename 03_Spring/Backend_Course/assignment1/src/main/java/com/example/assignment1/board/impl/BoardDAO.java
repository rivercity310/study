package com.example.assignment1.board.impl;

import com.example.assignment1.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {
    private JdbcTemplate jdbcTemplate;

    private String INSERT_BOARD = "INSERT INTO board(id, reward, position, content, tech, name, nation, location) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private String UPDATE_BOARD = "UPDATE board SET position=?, reward=?, content=?, tech=? WHERE seq=?";
    private String DELETE_BOARD = "DELETE FROM board WHERE seq=?";
    private String BOARD_GET = "SELECT * FROM board WHERE seq=?";
    private String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";
    private String BOARD_LIST_SEARCH = "SELECT * FROM board WHERE REGEXP_LIKE(CONCAT(name, tech, nation, location), ?) ORDER BY seq DESC";
    private String BOARD_CONTENT_LIST = "SELECT * FROM board WHERE seq!=? AND name=?";

    @Autowired
    public BoardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertBoard(BoardVO vo) {
        Object[] args = { vo.getId(), vo.getReward(), vo.getPosition(), vo.getContent(), vo.getTech(), vo.getName(), vo.getNation(), vo.getLocation() };
        jdbcTemplate.update(INSERT_BOARD, args);
    }

    public void updateBoard(BoardVO vo) {
        Object[] args = { vo.getPosition(), vo.getReward(), vo.getContent(), vo.getTech(), vo.getSeq() };
        jdbcTemplate.update(UPDATE_BOARD, args);
    }

    public void deleteBoard(BoardVO vo) {
        jdbcTemplate.update(DELETE_BOARD, vo.getSeq());
    }

    public BoardVO getBoard(BoardVO vo) {
        Object[] args = { vo.getSeq() };
        return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
    }

    public List<BoardVO> getBoardContentList(BoardVO vo) {
        Object[] args = { vo.getSeq(), vo.getName() };
        return jdbcTemplate.query(BOARD_CONTENT_LIST, args, new BoardRowMapper());
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        if (vo.getSearchKeyword() == null || vo.getSearchKeyword() == "") return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());

        Object[] args = { vo.getSearchKeyword() };
        List<BoardVO> searched = jdbcTemplate.query(BOARD_LIST_SEARCH, args, new BoardRowMapper());
        return searched;
    }

    static class BoardRowMapper implements RowMapper<BoardVO> {
        @Override
        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BoardVO vo = new BoardVO();
            vo.setSeq(rs.getInt("seq"));
            vo.setId(rs.getInt("id"));
            vo.setName(rs.getString("name"));
            vo.setNation(rs.getString("nation"));
            vo.setLocation(rs.getString("location"));
            vo.setReward(rs.getInt("reward"));
            vo.setPosition(rs.getString("position"));
            vo.setContent(rs.getString("content"));
            vo.setTech(rs.getString("tech"));

            return vo;
        }
    }
}
