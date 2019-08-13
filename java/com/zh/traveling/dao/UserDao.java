package com.zh.traveling.dao;

import com.zh.traveling.entity.User;

public interface UserDao {
    //根据用户名查询用户信息

    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    User findByCode(String code);
//更新用户的激活状态
    void updateStatus(User user);
    //根据用户名和密码查询用户
    User findByUsernameAndPassword(String username, String password);


}


