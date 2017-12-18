package com.account.service.impl;

import com.account.dao.UserDao;
import com.account.domain.User;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;


    public User findUser(User user) {
        return userDao.saveUser(user);
    }
}
