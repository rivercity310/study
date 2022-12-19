package com.springbook.biz.mvc.user;

import com.springbook.biz.mvc.user.impl.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
    @RequestMapping("/signin.do")
    public String signin(UserVO vo, UserDAO userDAO) {
        userDAO.insertUser(vo);
        return "login.jsp";
    }
}
