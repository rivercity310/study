package com.springbook.biz.mvc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/spring", "root", "8452994ash!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void close(PreparedStatement stmt, Connection conn) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (Exception e) { e.printStackTrace(); }
            finally { stmt = null; }
        }

        if (conn != null) {
            try { conn.close(); }
            catch (Exception e) { e.printStackTrace(); }
            finally { conn = null; }
        }
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if (rs != null) {
            try { rs.close(); }
            catch (Exception e) { e.printStackTrace(); }
            finally { rs = null; }
        }

        JDBCUtil.close(stmt, conn);
    }
}
