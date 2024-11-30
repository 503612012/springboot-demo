package com.oven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class ExecutorConfig {

    @Bean("myThreadPool")
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(2);
        // 配置最大线程数
        executor.setMaxPoolSize(4);
        // 配置队列大小
        executor.setQueueCapacity(10);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("任务线程-");
        // 执行初始化
        executor.initialize();
        return executor;
    }

}
