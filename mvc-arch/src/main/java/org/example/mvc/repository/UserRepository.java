package org.example.mvc.repository;

import org.example.mvc.annotation.Inject;
import org.example.mvc.annotation.Repository;
import org.example.mvc.modelAndView.view.model.User;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String USER_SAVE = "INSERT INTO usr(userId, name) VALUES(?, ?)";
    private final String USER_FIND_ALL = "SELECT * FROM usr";

    public UserRepository() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    public void save(User user) throws SQLException {
        jdbcTemplate.executeUpdate(USER_SAVE, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getName());
        });
    }

    public List<User> findAll() throws SQLException {
        return jdbcTemplate.executeQuery(USER_FIND_ALL);
    }
}
