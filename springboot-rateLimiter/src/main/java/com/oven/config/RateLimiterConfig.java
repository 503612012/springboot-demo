package com.oven.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    @SuppressWarnings("UnstableApiUsage")
    public RateLimiter rateLimiter() {
        // 每分钟两个许可
        double number = 2;
        long time = 60;
        return RateLimiter.create(number / time);
    }

}
