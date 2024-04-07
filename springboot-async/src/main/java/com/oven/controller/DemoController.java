package com.oven.controller;

import com.oven.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DemoController {

    @Resource
    private AsyncService asyncService;

    @RequestMapping("/test")
    public String test() {
        try {
            asyncService.doSomething();
            return "success";
        } catch (Exception e) {
            log.error("系统异常：", e);
            return "error";
        }
    }

}
