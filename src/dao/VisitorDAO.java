package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jspbean.VisitorVO;

public class VisitorDAO {
    public ArrayList<VisitorVO> listAll() {
        ArrayList<VisitorVO> result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/myoracle");
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, to_char(writedate, 'yyyy\"년\"mm\"월\"dd\"일\"'), content from visitor");

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
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/myoracle");
            conn = ds.getConnection();

            pstmt = conn.prepareStatement("insert into visitor (name, writedate, content) values(?, sysdate, ?)");
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getContent());

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
