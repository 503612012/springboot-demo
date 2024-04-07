package com.oven.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void doSomething() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            log.info("do something...{}", i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
