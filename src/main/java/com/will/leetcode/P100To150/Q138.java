package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        Node oldHead = head;
        List<Node> nodeList = new ArrayList<>();
        Map<Node,Integer> mp = new HashMap<>();
        int count= 0;
        Node newHead = new Node(head.val);
        nodeList.add(newHead);
        mp.put(head,count);
        Node curNode = newHead;
        while (head.next!=null){
            Node next = head.next;
            Node newNode = new Node(next.val);
            nodeList.add(newNode);
            mp.put(next,++count);
            curNode.next = newNode;
            head = next;
            curNode = newNode;
        }
        curNode = newHead;
        while (oldHead!=null){
            if (oldHead.random!=null){
                curNode.random = nodeList.get(mp.get(oldHead.random));
            }
            oldHead = oldHead.next;
            curNode = curNode.next;
        }
        return newHead;
    }

    public static void main(String[] args) {

    }
}
