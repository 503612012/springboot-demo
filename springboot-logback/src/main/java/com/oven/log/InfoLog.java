package com.oven.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class InfoLog implements CommandLineRunner {

    @Override
    public void run(String... args) {
        new Thread(() -> {
            while (true) {
                log.info("info日志。。。");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("系统异常：", e);
                }
            }
        }).start();
    }

}
