package com.oven.client;

import com.oven.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitMQClient {

    @Resource
    private RabbitMessagingTemplate rabbitM;

    public void send(String message) {
        rabbitM.convertAndSend(Constants.TOPIC, message);
    }

}
