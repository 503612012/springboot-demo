package com.oven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * 生产者
 */
@Slf4j
@Component
@EnableScheduling
public class KafkaProducer {

    private static int no = 1;

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
    @SuppressWarnings("unchecked")
    @Scheduled(cron = "0/1 * * * * ?")
    public void send() {
        no++;
        ListenableFuture future = kafkaTemplate.send("oven.test.topic", String.valueOf(no));
        future.addCallback(o -> log.info("send-消息发送成功：{}", no), throwable -> log.info("消息发送失败：{}", no));
    }

}