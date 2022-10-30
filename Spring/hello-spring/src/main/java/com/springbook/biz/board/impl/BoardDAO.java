package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO {
    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // SQL 명령어
    private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT nvl(max(SEQ), 0) + 1 FROM BOARD), ?, ?, ?)";
    private final String BOARD_UPDATE = "UPDATE BOARD SET title=?, content=? WHERE seq=?";
    private final String BOARD_DELETE = "DELETE FROM BOARD WHERE seq=?";
    private final String BOARD_GET = "SELECT * FROM BOARD WHERE seq=?";
    private final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY seq DESC";

    // CRUE 기능 메소드 구현
    public void insertBoard(BoardVO vo) throws SQLException {
        System.out.println("===> JDBC로 insert() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updateBoard(BoardVO vo) throws SQLException {
        System.out.println("===> JDBC로 update() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());

            stmt.executeUpdate();
        }
        finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void deleteBoard(BoardVO vo) throws SQLException {
        System.out.println("===> JDBC로 delete() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());

            stmt.executeUpdate();

        }
        finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) throws SQLException {
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        BoardVO board = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();

            if (rs.next()) {
                board = new BoardVO();

                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
            }

            return board;
        }
        finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo) throws SQLException {
        System.out.println("===> JDBC로 getBoardList 기능 처리");
        List<BoardVO> boardList = new ArrayList<BoardVO>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardVO board = new BoardVO();

                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));

                boardList.add(board);
            }

            return boardList;
        }
        finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}
