package org.example.mvc.repository;

import org.example.mvc.modelAndView.view.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;


class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws SQLException {
        userRepository = new UserRepository();

        User usr = new User("123", "123");
        userRepository.save(usr);
    }

    @Test
    void saveTest() throws SQLException {
        userRepository.save(new User("황승수", "황승수"));
    }

    @Test
    void findTest() throws SQLException {
        List<User> all = userRepository.findAll();

        for (User user : all) {
            System.out.println("user = " + user.getName());
        }
    }
}