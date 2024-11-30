package com.oven.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Runner2 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("项目启动后就会执行。。。222");
    }

}
