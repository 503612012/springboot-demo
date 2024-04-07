package com.oven.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/sendMsg")
    public String test() {
        jmsMessagingTemplate.convertAndSend("my_msg", "my name is oven");
        return "send success";
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Oven");
        map.put("age", "18");
        jmsMessagingTemplate.convertAndSend("my_map", map);
        log.info("map发送成功");
    }

}
