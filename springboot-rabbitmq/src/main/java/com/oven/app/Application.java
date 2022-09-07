package com.oven.app;

import com.oven.constants.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.oven")
public class Application {

    public static void main(String[] args) {
        // 安装RabbitMQ：docker run -d -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin --name rabbitmq rabbitmq:3-management
        SpringApplication.run(Application.class, args);
    }

    // 需要先把监听的队列创建好
    @Bean
    public Queue createQueue() {
        return new Queue(Constants.TOPIC);
    }

}
