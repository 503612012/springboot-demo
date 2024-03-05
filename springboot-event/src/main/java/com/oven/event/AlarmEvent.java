package com.oven.event;

import lombok.Data;

@Data
public class AlarmEvent {

    private String message;

    public AlarmEvent(String message) {
        this.message = message;
    }

}
