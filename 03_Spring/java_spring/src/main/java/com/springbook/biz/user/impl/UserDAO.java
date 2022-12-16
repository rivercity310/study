package com.springbook.biz.user.impl;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDAO")
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String USER_GET = "select * from Users where id=? and password=?";

    class UserRowMapper implements RowMapper<UserVO> {
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

    public UserVO getUser(UserVO vo) {
        System.out.println("===> Spring JDBC로 getUser() 기능 처리 ");
        Object[] args = { vo.getId(), vo.getPassword() };
        return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
    }
}
