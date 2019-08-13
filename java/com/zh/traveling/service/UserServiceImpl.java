package com.zh.traveling.service;

import com.zh.traveling.dao.UserDao;
import com.zh.traveling.dao.impl.UserDaoImpl;
import com.zh.traveling.entity.User;
import com.zh.traveling.util.MailUtils;
import com.zh.traveling.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //3.激活邮件发送，邮件正文？

        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【haohao旅游网】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }

//激活用户
    @Override
    public boolean active(String code) {
        //有两个功能
        //1.调用userDao通过激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    //登录方法
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    //根据用户名和密码查询用户
    @Override
    public User findByUsernameAndPassword(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
