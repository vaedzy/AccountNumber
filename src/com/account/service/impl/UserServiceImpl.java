package com.account.service.impl;

import com.account.dao.UserDao;
import com.account.domain.User;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByName(String username) {
        System.out.println("findUserByLoginName");
        return userDao.findUserByName(username);
    }
}
