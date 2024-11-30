package com.oven.neo4j.service;

import com.oven.neo4j.entity.Node;
import com.oven.neo4j.entity.Relation;

import java.util.List;

public interface NodeService {

    Node save(Node node);

    void bind(String name1, String name2, String relationName);

    List<Relation> parseAndBind(String sentence);

}