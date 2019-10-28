package com.oven;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * <p>
 * 使用@KafkaListener注解,可以指定:主题,分区,消费组
 *
 * @author Oven
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"icep_adapter_event_4g_group2"})
    public void receive(String message) {
        System.out.println("app_log_1--消费消息:" + message);
    }

}