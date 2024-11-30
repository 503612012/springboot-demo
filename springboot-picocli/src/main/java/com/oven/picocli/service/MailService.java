package com.oven.picocli.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    public void sendMessage(List<String> to, String subject, String body) {
        System.out.println("收件人：" + to);
        System.out.println("标题：" + subject);
        System.out.println("内容：" + body);
    }

}
