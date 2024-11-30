package com.oven.service;

import com.oven.entity.User;

public interface UserService {

    User findByUserName(String userName);

}