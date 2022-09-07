package com.oven.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统启动类
 *
 * @author Oven
 */
@SpringBootApplication(scanBasePackages = "com.oven")
public class Application {

    /**
     * 系统启动入口
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}