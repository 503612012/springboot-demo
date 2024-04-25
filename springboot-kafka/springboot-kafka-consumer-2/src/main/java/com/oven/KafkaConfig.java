package com.oven;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * kafka配置
 */
@Configuration
public class KafkaConfig implements InitializingBean {

    @Value("${spring.kafka.consumer.topicName}")
    private String topicName;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("topicName", topicName);
    }

}
