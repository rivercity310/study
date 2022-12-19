package com.springbook.biz.mvc.user;

import com.springbook.biz.mvc.user.impl.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(UserVO vo) {
        System.out.println("로그인 화면으로 이동");
        vo.setId("test111");
        vo.setPassword("123111");
        return "login.jsp";
    }

    @RequestMapping(value = "/login.do", method=RequestMethod.POST)
    public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
        UserVO user = userDAO.getUser(vo);
        if (user != null) {
            session.setAttribute("userName", user.getName());
            return "getBoardList.do";
        }
        return "login.jsp";
    }
}
