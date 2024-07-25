package com.oven.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LacciListener2 implements UpdateListener {

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            log.info(" 222 匹配成功，批到的基站为：{}，要发送的手机号为：{}，原始信息内容：{}", newEvents[0].get("lacci"), newEvents[0].get("phone"), newEvents[0].getUnderlying());
        }
    }

}
