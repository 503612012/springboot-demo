package com.oven.server;

import com.oven.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQServer {

    @RabbitListener(queues = Constants.TOPIC)
    public void receive(String message) {
        log.info("接收到的消息：{}", message);
    }

}
