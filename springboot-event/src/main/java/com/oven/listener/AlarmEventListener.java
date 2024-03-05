package com.oven.listener;

import com.oven.event.AlarmEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AlarmEventListener {

    @Async
    @EventListener
    public void opEvent(AlarmEvent event) throws InterruptedException {
        log.info("开始-收到evelt：{}", event.getMessage());
        TimeUnit.SECONDS.sleep(5);
        log.info("结束-收到evelt：{}", event.getMessage());
    }

}
