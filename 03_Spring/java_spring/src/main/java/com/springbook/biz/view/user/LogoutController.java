package com.springbook.biz.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login.jsp";
    }
}
