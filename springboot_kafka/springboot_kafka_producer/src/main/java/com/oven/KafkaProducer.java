package com.oven;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * 生产者
 *
 * @author Oven
 */
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
    @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        no++;
        ListenableFuture future = kafkaTemplate.send("test_topic1004", String.valueOf(no));
        future.addCallback(o -> System.out.println("send-消息发送成功：" + no), throwable -> System.out.println("消息发送失败：" + no));
    }

}