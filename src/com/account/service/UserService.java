package com.account.service;

import com.account.domain.User;

public interface UserService {
    public User getUserById(int id);
    public User findUserByName(String username);
}
