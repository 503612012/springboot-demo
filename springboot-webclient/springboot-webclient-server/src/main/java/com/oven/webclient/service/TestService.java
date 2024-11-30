package com.oven.webclient.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TestService {

    public Flux<String> flux() {
        return Flux.<String>create(sink -> Schedulers.parallel().schedule(() -> {
            for (int i = 1; i <= 5; i++) {
                String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
                log.info("当前时间：{} ", time);
                // 发送数据给客户端
                send(sink, time);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    sink.error(e);
                }
            }
            sink.complete();
        })).share(); // 允许多个订阅者共享数据流
    }

    private void send(FluxSink<String> sink, String time) {
        // 确保客户端没有取消订阅
        if (sink.isCancelled()) {
            log.error("客户端已断开");
            return;
        }
        sink.next("当前时间：" + time);
    }

}
