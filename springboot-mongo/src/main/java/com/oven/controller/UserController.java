package com.oven.controller;

import com.oven.service.UserService;
import com.oven.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public void add(User user) {
        userService.add(user);
    }

    @RequestMapping("/find")
    public User find(String userName, String tname) {
        return userService.findById(userName, tname);
    }

}
