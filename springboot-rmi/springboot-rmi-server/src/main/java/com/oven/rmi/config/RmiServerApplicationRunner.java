package com.oven.rmi.config;

import com.oven.rmi.common.service.StudentService;
import com.oven.rmi.service.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;

@Component
public class RmiServerApplicationRunner {

    private final StudentServiceImpl studentService;

    public RmiServerApplicationRunner(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @Bean
    public RmiServiceExporter studentServiceExporter() {
        RmiServiceExporter studentServiceExporter = new RmiServiceExporter();
        studentServiceExporter.setRegistryPort(5678);
        studentServiceExporter.setServiceName("studentService");
        studentServiceExporter.setServiceInterface(StudentService.class);
        studentServiceExporter.setService(studentService);
        System.out.println("Started Student RMI Server");
        return studentServiceExporter;
    }

}