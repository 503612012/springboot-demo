package com.oven.service.impl;

import com.oven.service.IService;
import org.springframework.stereotype.Service;

@Service
public class Service01Impl implements IService {
    @Override
    public String serviceId() {
        return "01";
    }

    @Override
    public String doSomthing(String name) {
        return name + "01";
    }
}
