package com.oven.zipkin.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @RequestMapping("ping")
    public Object ping() {
        log.info("[producer]=========================== >>> ping");
        return "pong";
    }

    @RequestMapping("log")
    public Object log() {
        log.debug("[producer]=========================== >>> debug log");
        log.info("[producer]=========================== >>> info log");
        log.warn("[producer]=========================== >>> warn log");
        log.error("[producer]=========================== >>> error log");
        log.trace("[producer]=========================== >>> trace log");
        return "log";
    }

}