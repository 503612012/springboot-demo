package com.oven.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TaskThread implements Runnable {

    private final String key;

    private TaskThread(String key) {
        this.key = key;
    }

    public static TaskThread build(String key) {
        return new TaskThread(key);
    }

    @Override
    public void run() {
        try {
            log.info("=========================== >>> 开始执行任务：{}", key);
            TimeUnit.SECONDS.sleep(2);
            log.info("=========================== >>> 结束执行任务：{}", key);
        } catch (Exception e) {
            log.error("任务线程异常：", e);
        }
    }

}
