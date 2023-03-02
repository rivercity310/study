package org.example.mvc.repository;

import org.example.mvc.modelAndView.view.model.User;

import java.util.*;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    public static void save(User user) {
        users.put(user.getUserId(), user);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
