package com.springbook.biz.mvc.user.impl;

import com.springbook.biz.mvc.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDAO")
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    private final String USER_GET = "select * from Users where id=? and password=?";
    private final String USER_INSERT = "INSERT INTO Users VALUES(?, ?, ?, ?)";

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserVO getUser(UserVO vo) {
        Object[] args = { vo.getId(), vo.getPassword() };
        return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());

        /*
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserVO user = new UserVO();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getString("Id"));
                user.setPassword(rs.getString("Password"));
                user.setName(rs.getString("Name"));
                user.setRole(rs.getString("Role"));

                return user;
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(rs, stmt, conn); }

        return null;
        */
    }

    public void insertUser(UserVO vo) {
        Object[] args = { vo.getId(), vo.getPassword(), vo.getName(), "User" };
        jdbcTemplate.update(USER_INSERT, args);

        /*
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_INSERT);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, "User");

            stmt.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { JDBCUtil.close(stmt, conn); }
        */
    }

    static class UserRowMapper implements RowMapper<UserVO> {
        @Override
        public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserVO vo = new UserVO();
            vo.setId(rs.getString("id"));
            vo.setPassword(rs.getString("password"));
            vo.setName(rs.getString("name"));
            vo.setRole(rs.getString("role"));

            return vo;
        }
    }
}
