package com.oven.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@SuppressWarnings("UnstableApiUsage")
public class TestController {

    @Resource
    private RateLimiter rateLimiter;

    @RequestMapping("/test")
    public Object test() throws InterruptedException {
        boolean flag = rateLimiter.tryAcquire(2, TimeUnit.SECONDS);
        if (flag) {
            TimeUnit.SECONDS.sleep(5);
            return "成功";
        }
        return "失败";
    }

}
