package com.springbook.biz.common;

import javax.naming.NamingException;
import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/MySpring", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void close(PreparedStatement stmt, Connection conn) throws SQLException {
        if (!stmt.isClosed())
            stmt.close();

        if (!conn.isClosed())
            conn.close();
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) throws SQLException {
        if (!rs.isClosed())
            rs.close();

        close(stmt, conn);
    }
}
