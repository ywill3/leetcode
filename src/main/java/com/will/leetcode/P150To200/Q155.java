package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Q155 {

    Stack<Node> nodesStack;
    Node head;
    Node tail;
    List<Node> minNodes;

    public class Node {
        Node pre,next;
        int val;
        Node() {}
        Node(int val) { this.val = val; }
    }
    public Q155() {
        nodesStack = new Stack<>();
        head = new Node(0);
        tail = head;
        minNodes = new ArrayList<>();
    }

    public void push(int val) {
        Node node = new Node(val);
        // 加入排序列表
        addSortList(0,minNodes.size()-1,node);
        // 插入栈
        nodesStack.push(node);
    }

    private void addSortList(int start,int end,Node node) {
        if (minNodes.isEmpty()){
            minNodes.add(node);
            return;
        }
        for (int i=start;i<=end;i++){
            if (minNodes.get(i).val>=node.val){
                minNodes.add(i,node);
                return;
            }
        }
        /*if (start==minNodes.size()){
            minNodes.add(start,node);
            return;
        }
        if (start==end){
            if (minNodes.get(start).val>=node.val){
                minNodes.add(start,node);
            }else {
                minNodes.add(start+1,node);
            }
            return;
        }
        if (end==0){
            minNodes.add(end,node);
            return;
        }

        if (minNodes.get(start).val>=node.val){
            minNodes.add(start,node);
        }
        else if (minNodes.get(end).val<=node.val){
            minNodes.add(end,node);
        }else {
            int mid = (end-start)/2;
            if (minNodes.get(mid).val>node.val){
                addSortList(0,mid-1,node);
            }else {
                addSortList(mid+1,end,node);
            }
        }*/
    }

    public void pop() {
        Node pop = nodesStack.pop();
        // 删除节点
        minNodes.remove(pop);
    }

    public int top() {
        if (!nodesStack.isEmpty()){
            return nodesStack.peek().val;
        }
        return 0;
    }

    public int getMin() {
        if (!minNodes.isEmpty()){
            return minNodes.get(0).val;
        }
        return 0;
    }

    public static void main(String[] args) {
        Q155 q = new Q155();
        q.push(-2);
        q.push(0);
        q.push(-3);
        System.out.println(q.getMin());
        q.pop();
        System.out.println(q.top());
        System.out.println(q.getMin());
    }

}
