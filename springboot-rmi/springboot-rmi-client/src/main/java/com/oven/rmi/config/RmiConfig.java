package com.oven.rmi.config;

import com.oven.rmi.common.service.StudentService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
public class RmiConfig {

    @Bean
    public RmiProxyFactoryBean proxyFactoryBean() {
        String remoteHost = "localhost";
        String rmiHost = String.format("rmi://%s:5678/studentService", remoteHost);
        System.out.println("RMI Server Address is " + rmiHost);
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(StudentService.class);
        proxy.setServiceUrl(rmiHost);
        proxy.afterPropertiesSet();
        return proxy;
    }

}
