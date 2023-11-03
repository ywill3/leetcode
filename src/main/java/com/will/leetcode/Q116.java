package com.will.leetcode;

import java.util.*;

public class Q116 {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null)
            return null;
        Node cur = root;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(cur);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i=0;i<size;i++){
                Node tmp = nodes.poll();
                if(i==size-1) {
                    tmp.next = null;
                }else {
                    tmp.next = nodes.peek();
                }
                if (tmp.left != null) {
                    nodes.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodes.add(tmp.right);
                }
            }
        }
        return cur;
    }

    public static void main(String[] args) {

    }

}
