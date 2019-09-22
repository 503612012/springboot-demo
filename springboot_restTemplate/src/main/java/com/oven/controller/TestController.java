package com.oven.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 测试类
 *
 * @author Oven
 */
@RestController
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public String test() throws URISyntaxException {
        String ip = "36.110.15.94";
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String result = restTemplate.postForObject(new URI(url), null, String.class);
        System.out.println(result);
        return result;
    }

}
