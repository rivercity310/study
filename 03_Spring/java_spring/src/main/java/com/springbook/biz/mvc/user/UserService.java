package com.springbook.biz.mvc.user;

public interface UserService {
    UserVO getUser(UserVO vo);
    void insertUser(UserVO vo);
}
