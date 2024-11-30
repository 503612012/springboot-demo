package com.oven.webclient.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {

    @Resource
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/test")
    public Object test(String type) {
        log.info("开始测试");
        WebClient webClient = webClientBuilder.baseUrl("http://127.0.0.1:8080").build();

        JSONObject body = new JSONObject();
        body.put("type", type);
        webClient.post()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_EVENT_STREAM)
                .bodyValue(body)
                .exchangeToFlux(clientResponse -> {
                    log.info("http状态码：{}", clientResponse.rawStatusCode());
                    log.info("http响应头：{}", clientResponse.headers().contentType());
                    return clientResponse.bodyToFlux(String.class)
                            .doOnNext(msg -> log.info("收到服务端响应信息：{}", msg))
                            .doOnError(throwable -> log.error("内层报错了：", throwable));
                })
                .doOnError(throwable -> log.error("外部异常：", throwable))
                .subscribe();
        log.info("测试结束");
        return JSONObject.parse("{'code': 200, 'msg': 'success'}");
    }

}
