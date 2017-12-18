package com.account.service.impl;

import com.account.dao.UserDao;
import com.account.domain.User;
import com.account.service.UserService;

public class UserServiceImpl extends UserService{
    private UserDao userDao;

    @Override
    public User findUser(User user) {
        return userDao.saveUser(user);
    }
}
