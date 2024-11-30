package com.oven.neo4j.controller;

import com.oven.neo4j.entity.Node;
import com.oven.neo4j.service.NodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private NodeService nodeService;

    @GetMapping("save")
    public Object save(Long id, String name, String title) {
        Node node = new Node();
        node.setId(id);
        node.setName(name);
        node.setTitle(title);
        return nodeService.save(node);
    }

    @GetMapping("bind")
    public Object bind(String name1, String name2, String relationName) {
        nodeService.bind(name1, name2, relationName);
        return "成功";
    }

    @GetMapping("parse")
    public Object parse(String sentence) {
        return nodeService.parseAndBind(sentence);
    }

}