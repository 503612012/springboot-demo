package com.oven.controller;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.oven.config.EsperConfig;
import com.oven.listener.LacciListener1;
import com.oven.listener.LacciListener2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class EplController {

    @Resource(name = "epServiceProvider")
    private EPServiceProvider provider;
    @Resource(name = "epAdministrator")
    private EPAdministrator epAdministrator;

    @RequestMapping("/start1")
    public String start1() {
        if (null != EsperConfig.STATEMENT_WRAP.get("1")) {
            return "esper1已经启动！";
        }

        String epl = "select lacci, phone from eventLocation where lacci in ('2', '1', '3')";
        EPStatement statement = epAdministrator.createEPL(epl);
        statement.addListener(new LacciListener1());
        statement.start();
        EsperConfig.STATEMENT_WRAP.put("1", statement);
        log.info(" 111 启动成功！");
        return " 111 启动成功！";
    }

    @RequestMapping("/start2")
    public String start2() {
        if (null != EsperConfig.STATEMENT_WRAP.get("2")) {
            return "esper2已经启动！";
        }

        String epl = "select lacci, phone from eventLocation where lacci in ('4', '3', '5')";
        EPStatement statement = epAdministrator.createEPL(epl);
        statement.addListener(new LacciListener2());
        statement.start();
        EsperConfig.STATEMENT_WRAP.put("2", statement);
        log.info(" 222 启动成功！");
        return " 222 启动成功！";
    }

    @RequestMapping("/stop1")
    public String stop1() {
        EPStatement statement = EsperConfig.STATEMENT_WRAP.get("1");
        if (null == statement) {
            return "esper1 not exist!";
        }

        statement.stop();
        EsperConfig.STATEMENT_WRAP.remove("1");
        return " 111 停止成功！";
    }

    @RequestMapping("/stop2")
    public String stop2() {
        EPStatement statement = EsperConfig.STATEMENT_WRAP.get("2");
        if (null == statement) {
            return "esper2 not exist!";
        }

        statement.stop();
        EsperConfig.STATEMENT_WRAP.remove("2");
        return " 222 停止成功！";
    }

    @RequestMapping("/send")
    public String send(String lacci, String phone) {
        EPRuntime runtime = provider.getEPRuntime();
        Map<String, Object> map = new HashMap<>();
        map.put("lacci", lacci);
        map.put("phone", phone);

        runtime.sendEvent(map, "eventLocation");
        return "发送成功！";
    }

}
