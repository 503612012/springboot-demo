package com.oven.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 */
@EnableJpaRepositories(basePackages = "com.oven.dao")
@SpringBootApplication(scanBasePackages = "com.oven")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}