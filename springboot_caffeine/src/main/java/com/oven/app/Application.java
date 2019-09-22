package com.oven.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 *
 * @author Oven
 */
@SpringBootApplication(scanBasePackages = "com.oven")
@EnableCaching
public class Application {

    /**
     * 系统入口
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
