package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;
public class Q133 {

// Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Node[] nodes = new Node[101];
        Node nRoot = new Node(node.val,new ArrayList<>());
        digui(node,nRoot,nodes);
        return nRoot;
    }

    private void digui(Node node, Node nRoot, Node[] nodes) {
        nodes[nRoot.val] = nRoot;
        for (Node node1:node.neighbors){
            if (nodes[node1.val]==null){
                Node newR = new Node(node1.val,new ArrayList<>());
                digui(node1,newR,nodes);
            }
            nRoot.neighbors.add(nodes[node1.val]);
        }
    }

}
