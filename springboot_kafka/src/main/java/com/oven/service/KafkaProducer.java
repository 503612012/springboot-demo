package com.oven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产者
 *
 * @author Oven
 */
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息到topic
     */
    public void send(String topic, String msg) {
        try {
            kafkaTemplate.send(topic, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
