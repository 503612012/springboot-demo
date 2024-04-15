package com.oven.controller;

import com.oven.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DemoController {

    @Resource
    private RocketMQProducer rocketMQProducer;

    @RequestMapping("/send")
    public String send(String msg) {
        DefaultMQProducer producer = rocketMQProducer.getRocketMQProducer();
        Message message = new Message("springboot-rocketmq", "test", msg.getBytes());
        try {
            producer.send(message);
        } catch (Exception e) {
            log.error("系统异常：", e);
        }
        return "发送成功";
    }

}
