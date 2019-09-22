package com.oven.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * 消费者
 *
 * @author Oven
 */
@Service
public class KafkaConsumer {

    /**
     * 监听主题
     */
    @KafkaListener(topics = {"com.asiainfo.bdx.sms.realtime"})
    public void consumer(String message) {
        System.out.println("get springboot topic message of: " + message);
    }

}
