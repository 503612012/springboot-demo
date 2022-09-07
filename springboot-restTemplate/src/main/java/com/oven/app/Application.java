package com.oven.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统入口
 *
 * @author Oven
 */
@SpringBootApplication(scanBasePackages = "com.oven")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
