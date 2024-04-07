package com.oven.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class Consumer {

    @JmsListener(destination = "my_msg")
    public void readMsg(String text) {
        log.info("接收到的消息：{}", text);
    }

    @JmsListener(destination = "my_map")
    public void readMap(Map map) {
        log.info("接收到的Map：{}", map);
    }

}
