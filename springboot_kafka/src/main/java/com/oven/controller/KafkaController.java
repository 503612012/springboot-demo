package com.oven.controller;

import com.oven.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @ResponseBody
    @RequestMapping("/send")
    public Object send(String topic, String msg) {
        kafkaProducer.send(topic, msg);
        return "success";
    }

}
