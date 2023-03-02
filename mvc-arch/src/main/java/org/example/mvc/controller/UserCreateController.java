package org.example.mvc.controller;

import org.example.mvc.modelAndView.view.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uId = request.getParameter("userId");
        String uName = request.getParameter("userName");

        UserRepository.save(new User(uId, uName));
        return "redirect:/users";
    }
}
