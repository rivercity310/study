package com.springbook.biz.user.impl;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDAO")
public class UserDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private final String USER_GET = "select * from Users where id=? and password=?";

    public UserVO getUser(UserVO vo) {
        UserVO user = null;

        try {
            System.out.println("===> JDBC로 getUser() 기능 처리");
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());

            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("Id"));
                user.setPassword(rs.getString("Password"));
                user.setName(rs.getString("Name"));
                user.setRole(rs.getString("Role"));
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(rs, stmt, conn); }

        return user;
    }
}
