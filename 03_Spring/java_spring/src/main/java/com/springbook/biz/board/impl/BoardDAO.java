package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // private final String BOARD_INSERT_TEST = "INSERT INTO Board(Seq, Title, Writer, Content) VALUES(?, ?, ?, ?)";
    private final String BOARD_INSERT = "INSERT INTO Board(Title, Writer, Content) VALUES(?, ?, ?)";
    private final String BOARD_UPDATE = "UPDATE Board SET Title=?, Content=?, WHERE Seq=?";
    private final String BOARD_DELETE = "DELETE Board WHERE Seq=?";
    private final String BOARD_GET = "SELECT * FROM Board WHERE Seq=?";
    private final String BOARD_LIST = "SELECT * FROM Board ORDER BY Seq DESC";

    static class BoardRowMapper implements RowMapper<BoardVO> {
        @Override
        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BoardVO board = new BoardVO();

            board.setSeq(rs.getInt("Seq"));
            board.setTitle(rs.getString("Title"));
            board.setWriter(rs.getString("Writer"));
            board.setContent(rs.getString("Content"));
            board.setRegDate(rs.getDate("Regdate"));
            board.setCnt(rs.getInt("Cnt"));

            return board;
        }
    }

    public void insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard 기능 처리");
        // Object[] args = { vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent() };
        // jdbcTemplate.update(BOARD_INSERT_TEST, args);
        jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());

        /*
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());

            stmt.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(stmt, conn); }
        */
    }

    public void updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard 기능 처리");
        jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());

        /*
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());

            stmt.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(stmt, conn); }
         */
    }

    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JDBC로 deleteBoard 기능 처리");
        jdbcTemplate.update(BOARD_DELETE, vo.getSeq());

        /*
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(stmt, conn); }
         */
    }

    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> JDBC로 getBoard 기능 처리");
        Object[] args = { vo.getSeq() };
        return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());

        /*
        BoardVO board = null;

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();

            if (rs.next()) {
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("Title"));
                board.setContent(rs.getString("Content"));
                board.setWriter(rs.getString("Writer"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(rs, stmt, conn); }

        return board;
         */
    }

    public List<BoardVO> getBoardList() {
        System.out.println("===> JDBC로 getBoardList 기능 처리");
        return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());

        /*
        List<BoardVO> boardList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardVO board = new BoardVO();

                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("Title"));
                board.setContent(rs.getString("Content"));
                board.setWriter(rs.getString("Writer"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));

                boardList.add(board);
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(rs, stmt, conn); }

        return boardList;

         */
    }
}
