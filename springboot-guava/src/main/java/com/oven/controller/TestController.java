package com.oven.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @RequestMapping("/test")
    @Cacheable(value = "test")
    public Object test() {
        log.info("进入方法内部");
        return "hello guava";
    }

    @RequestMapping("/save")
    @CachePut(value = "name", key = "#name")
    public Object save(String name) {
        log.info("添加key为[{}]的缓存", name);
        return name;
    }

    @RequestMapping("/delete")
    @CacheEvict(value = "name", key = "#name")
    public void delete(String name) {
        log.info("删除key为[{}]的缓存", name);
    }

    @RequestMapping("/getByName")
    @Cacheable(value = "name", key = "#name")
    public Object getByName(String name) {
        log.info("添加key为[{}]的缓存", name);
        return name;
    }

}
