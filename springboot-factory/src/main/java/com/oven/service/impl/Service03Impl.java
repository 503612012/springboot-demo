package com.oven.service.impl;

import com.oven.service.IService;
import org.springframework.stereotype.Service;

@Service
public class Service03Impl implements IService {
    @Override
    public String serviceId() {
        return "03";
    }

    @Override
    public String doSomthing(String name) {
        return name + "03";
    }
}
