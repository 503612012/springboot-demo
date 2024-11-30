package com.oven.rmi.controller;

import com.oven.rmi.common.service.StudentService;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RmiProxyFactoryBean proxyFactoryBean;

    @RequestMapping("/test")
    public Object test(Integer id) {
        StudentService studentService = (StudentService) proxyFactoryBean.getObject();
        return studentService.getById(id);
    }

}
