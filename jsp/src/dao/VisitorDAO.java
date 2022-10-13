package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import jspbean.VisitorVO;

public class VisitorDAO {
    public ArrayList<VisitorVO> listAll() {
        ArrayList<VisitorVO> result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.get();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, writedate, content from visitor");

            result = new ArrayList<VisitorVO>();
            VisitorVO vo = null;
            while (rs.next()) {
                vo = new VisitorVO();
                vo.setName(rs.getString(1));
                vo.setWriteDate(rs.getString(2));
                vo.setContent(rs.getString(3));
                result.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean insert(VisitorVO vo) {
        boolean result = true;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionPool.get();

            pstmt = conn.prepareStatement("insert into visitor (name, writedate, content) values(?, ?, ?)");
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")));
            pstmt.setString(3, vo.getContent());

            int su = pstmt.executeUpdate();
            if (su != 1) result = false;

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
