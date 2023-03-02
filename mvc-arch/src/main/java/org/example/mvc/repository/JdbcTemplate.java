package org.example.mvc.repository;

import org.example.mvc.modelAndView.view.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
        try(
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pss.setter(pstmt);
            pstmt.executeUpdate();
        }
    }

    public List<User> executeQuery(String sql) throws SQLException {
        List<User> findUsers = new ArrayList<>();

        try(
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String name = resultSet.getString("name");
                findUsers.add(new User(userId, name));
            }
        }

        return findUsers;
    }
}
