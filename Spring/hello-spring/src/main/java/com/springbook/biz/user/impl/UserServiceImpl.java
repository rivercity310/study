package com.springbook.biz.user.impl;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;        // 이 객체를 이용하여 DB 연동 처리

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /*
    // Setter Injection 처리를 위한 Setter Method
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    */

    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }
}
