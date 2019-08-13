package com.zh.traveling.service;

import com.zh.traveling.entity.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);   //注册

    boolean active(String code);  //激活

    User login(User user);     //登录
//根据用户名和密码查询用户
    User findByUsernameAndPassword(User user);
}
