package org.example.mvc.controller;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.Inject;
import org.example.mvc.service.UserService;

@Controller
public record UserController(UserService userService) {
    @Inject
    public UserController {
    }
}
