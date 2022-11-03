package com.springbook.biz.user.impl;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDAO")
public class UserDAO {
    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // SQL
    private final String USER_GET = "SELECT * FROM USERS WHERE ID = ? AND PASSWORD = ?";

    // CRUD 기능 메소드 구현

    public UserVO getUser(UserVO vo) {
        UserVO user = null;

        try {
            System.out.println("===> JDBC로 getUser() 기능을 처리합니다.");

            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                JDBCUtil.close(rs, stmt, conn);
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
