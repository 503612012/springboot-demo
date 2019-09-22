package com.oven.util;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.util.HashMap;
import java.util.Map;

public class EsperUtil {

    private static EPAdministrator administrator;
    private static EPServiceProvider provider;

    static {
        provider = EPServiceProviderManager.getDefaultProvider();
        administrator = provider.getEPAdministrator();

        Map<String, Object> eventLocation = new HashMap<>();
        eventLocation.put("lacci", String.class);
        eventLocation.put("phone", Integer.class);

        // 注册eventLocation到esper
        administrator.getConfiguration().addEventType("eventLocation", eventLocation);
    }

    public static EPAdministrator getAdministrator() {
        return administrator;
    }

    public static EPServiceProvider getProvider() {
        return provider;
    }

}
