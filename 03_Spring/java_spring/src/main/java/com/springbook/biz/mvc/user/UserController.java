package com.springbook.biz.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginGet(UserVO vo) {
        vo.setId("test");
        vo.setPassword("123");
        return "login.jsp";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String loginPost(UserVO vo, HttpSession session) {
        if (vo.getId() == null || vo.getId() == "")
            throw new IllegalArgumentException("id 입력");

        UserVO user = userService.getUser(vo);
        if (user != null) {
            session.setAttribute("userName", user.getName());
            return "getBoardList.do";
        }
        return "login.jsp";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login.jsp";
    }

    @RequestMapping("/signin.do")
    public String signin(UserVO vo) {
        userService.insertUser(vo);
        return "login.jsp";
    }
}
