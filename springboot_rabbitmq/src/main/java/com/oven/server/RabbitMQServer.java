package com.oven.server;

import com.oven.constants.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQServer {

    @RabbitListener(queues = Constants.TOPIC)
    public void receive(String message) {
        System.out.println("接收到的消息：" + message);
    }

}
