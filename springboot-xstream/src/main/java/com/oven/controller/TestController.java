package com.oven.controller;

import com.oven.entity.Student;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/xml")
    public String xml() {
        Student student = Student.builder()
                .id(1)
                .user_name("zhangsan")
                .name("张三")
                .code("S001")
                .phone("15712345678")
                .age(18)
                .score(98.8)
                .isLeader(false)
                .build();
        XStream xStream = new XStream(new DomDriver());
        return xStream.toXML(student);
    }

    @RequestMapping("/entity")
    public Student entity() {
        Student student = Student.builder()
                .id(1)
                .user_name("zhangsan")
                .name("张三")
                .code("S001")
                .phone("15712345678")
                .age(18)
                .score(98.8)
                .isLeader(false)
                .build();
        XStream xStream = new XStream(new DomDriver());
        String xml = xStream.toXML(student);
        xStream.addPermission(AnyTypePermission.ANY);
        return (Student) xStream.fromXML(xml);
    }

}
