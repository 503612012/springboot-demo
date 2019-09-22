package com.oven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.oven.service.HelloService;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String SayHello(String name) {
        return "Hello , " + name;
    }
}
