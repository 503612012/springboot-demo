package com.oven.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    /**
     * 响应数据-加密
     */
    @RequestMapping(value = "/get")
    public Object get() {
        JSONObject result = new JSONObject();
        result.put("name", "Oven");
        result.put("encry", true);
        return result;
    }

    /**
     * 获取解密后的请求参数
     */
    @RequestMapping(value = "/send")
    public Object send(String name, String sex, Integer age) {
        log.info("controller接收的参数name={}、sex={}、age={}", name, sex, age);
        return new JSONObject();
    }
}