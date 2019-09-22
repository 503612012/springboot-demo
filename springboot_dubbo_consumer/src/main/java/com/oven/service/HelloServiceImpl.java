package com.oven.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Reference(version = "1.0.0")
    HelloServiceImpl helloService;

    @Override
    public String SayHello(String name) {
        return helloService.SayHello(name);
    }

}
