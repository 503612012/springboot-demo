package com.oven.controller;

import com.oven.event.AlarmEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping("/test")
    public String test(String msg) {
        StopWatch stopWatch = new StopWatch("test-event");
        stopWatch.start();
        eventPublisher.publishEvent(new AlarmEvent(msg));
        stopWatch.stop();
        log.info("发送event耗时：{}", stopWatch.prettyPrint());
        return msg;
    }

}
