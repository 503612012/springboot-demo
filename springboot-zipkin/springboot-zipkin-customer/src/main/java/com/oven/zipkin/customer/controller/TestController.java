package com.oven.zipkin.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@Slf4j
@RestController
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("test")
    public Object test() {
        URI pingUri = URI.create("http://localhost:8080/ping");
        String pingResponse = restTemplate.getForObject(pingUri, String.class);
        log.info("call ping result is {}", pingResponse);

        URI logUri = URI.create("http://localhost:8080/log");
        String logResponse = restTemplate.getForObject(logUri, String.class);
        log.info("call log result is {}", logResponse);

        String secondCallPingResponse = restTemplate.getForObject(pingUri, String.class);
        log.info("second call ping result is {}", secondCallPingResponse);
        return pingResponse + "-------" + logResponse + "-------" + secondCallPingResponse + "-------";
    }

}