package org.example.mvc.controller;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.Inject;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.modelAndView.view.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserCreateController {
    private final UserRepository userRepository;

    @Inject
    public UserCreateController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uId = request.getParameter("userId");
        String uName = request.getParameter("userName");

        userRepository.save(new User(uId, uName));
        return "redirect:/user/list";
    }
}
