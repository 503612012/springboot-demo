package com.oven.controller;

import com.oven.client.RabbitMQClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RabbitMQClient rabbitMQClient;

    @RequestMapping("/send")
    public Object send(String message) {
        rabbitMQClient.send(message);
        return "发送成功！";
    }

}
