package com.oven.webclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.oven.webclient.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @PostMapping(value = "/flux")
    public Object flux(@RequestBody JSONObject params) {
        if ("flux".equals(params.getString("type"))) {
            return testService.flux();
        } else if ("json".equals(params.getString("type"))) {
            JSONObject obj = new JSONObject();
            obj.put("name", "Oven");
            obj.put("age", 18);
            return obj;
        } else {
            return new ResponseEntity<>("模拟服务端报错", HttpStatus.SEE_OTHER);
        }
    }

}
