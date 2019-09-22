package com.oven.controller;

import com.oven.service.HelloServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private HelloServiceImpl helloService;

    @RequestMapping("/test")
    public Object test(String name) {
        return helloService.SayHello(name);
    }

}
