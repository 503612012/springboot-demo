package com.oven.sevice.impl;

import com.oven.dao.UserDao;
import com.oven.entity.User;
import com.oven.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}