package com.springbook.biz.view.user;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
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
