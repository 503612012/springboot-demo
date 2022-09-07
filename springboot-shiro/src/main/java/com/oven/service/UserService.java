package com.oven.service;

import com.oven.vo.User;

public interface UserService {

    User findByUserName(String userName);

}