package com.oven.config;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EsperConfig {

    public final static Map<String, EPStatement> statementWrap = new HashMap<>();

    @Bean
    public EPServiceProvider epServiceProvider() {
        com.espertech.esper.client.Configuration config = new com.espertech.esper.client.Configuration();

        Map<String, Object> eventLocation = new HashMap<>();
        eventLocation.put("lacci", String.class);
        eventLocation.put("phone", Integer.class);

        config.addEventType("eventLocation", eventLocation);

        return EPServiceProviderManager.getDefaultProvider(config);
    }

    @Bean
    public EPAdministrator epAdministrator() {
        return epServiceProvider().getEPAdministrator();
    }

}
