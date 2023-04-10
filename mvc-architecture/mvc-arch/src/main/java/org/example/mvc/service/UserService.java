package org.example.mvc.service;

import org.example.mvc.annotation.Inject;
import org.example.mvc.annotation.Service;
import org.example.mvc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Inject
    protected UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
