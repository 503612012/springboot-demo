package com.oven.neo4j.util;

import edu.stanford.nlp.trees.TreeGraphNode;

import java.util.Objects;

public class GraphUtil {

    public static String getNodeValue(TreeGraphNode treeGraphNode) {
        return Objects.nonNull(treeGraphNode) ? treeGraphNode.toString("value") : null;
    }

}