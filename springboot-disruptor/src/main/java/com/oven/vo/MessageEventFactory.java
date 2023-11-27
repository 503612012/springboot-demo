package com.oven.vo;

import com.lmax.disruptor.EventFactory;

public class MessageEventFactory implements EventFactory<Message> {

    @Override
    public Message newInstance() {
        return new Message();
    }

}
