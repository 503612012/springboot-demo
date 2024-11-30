package com.oven.config;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
@Component
public class MemcachedRunner implements CommandLineRunner {

    @Resource
    private MemcacheSource memcacheSource;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) {
        try {
            client = new MemcachedClient(new InetSocketAddress(memcacheSource.getIp(), memcacheSource.getPort()));
        } catch (IOException e) {
            log.error("系统异常：", e);
        }
    }

    public MemcachedClient getClient() {
        return client;
    }

}