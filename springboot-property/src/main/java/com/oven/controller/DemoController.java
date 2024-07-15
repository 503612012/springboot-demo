package com.oven.controller;

import com.oven.entity.User;
import com.oven.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DemoController {

    @Value("${app.info.author}")
    private String author;

    @Resource
    private RedisService redisService;

    @RequestMapping("/redis")
    public String redis() {
        log.info(redisService.get("name"));
        redisService.set("name", "Oven");
        log.info(redisService.get("name"));
        redisService.remove("name");
        log.info(redisService.get("name"));

        log.info("{}", (Integer) redisService.get("age"));
        redisService.set("age", 18);
        log.info("{}", (Integer) redisService.get("age"));
        redisService.remove("age");
        log.info("{}", (Integer) redisService.get("age"));

        log.info("{}", redisService.contains("redis"));
        redisService.set("redis", "redis");
        log.info("{}", redisService.contains("redis"));

        User user = new User();
        user.setName("Oven");
        user.setAge(18);
        user.setScore(88.88);

        log.info("{}", (User) redisService.get("user"));
        redisService.set("user", user);
        log.info("{}", (User) redisService.get("user"));
        redisService.remove("user");
        log.info("{}", (User) redisService.get("user"));

        return "测试完毕";
    }

    @RequestMapping("/get")
    public String get() {
        return author;
    }

}
