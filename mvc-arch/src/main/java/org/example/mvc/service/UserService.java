package org.example.mvc.service;

import org.example.mvc.annotation.Inject;
import org.example.mvc.annotation.Service;
import org.example.mvc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
