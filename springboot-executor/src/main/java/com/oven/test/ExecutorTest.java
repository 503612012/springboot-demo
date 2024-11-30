package com.oven.test;

import com.oven.thread.TaskThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class ExecutorTest implements CommandLineRunner {

    @Resource(name = "myThreadPool")
    private Executor executor;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 10; i++) {
            executor.execute(TaskThread.build("KEY_" + i));
        }
    }

}
