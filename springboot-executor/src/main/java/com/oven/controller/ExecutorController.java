package com.oven.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@RestController
public class ExecutorController {

    @Resource(name = "myThreadPool")
    private Executor executor;

    @RequestMapping("/info")
    public Object info() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) executor;
        JSONObject result = new JSONObject();

        result.put("ActiveCount", threadPoolTaskExecutor.getActiveCount());
        result.put("PoolSize", threadPoolTaskExecutor.getPoolSize());
        result.put("CorePoolSize", threadPoolTaskExecutor.getCorePoolSize());
        result.put("MaxPoolSize", threadPoolTaskExecutor.getMaxPoolSize());
        result.put("KeepAliveSeconds", threadPoolTaskExecutor.getKeepAliveSeconds());
        int size = threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size();
        result.put("QueueSize", size);
        return result;
    }

}
