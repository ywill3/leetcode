package com.will.leetcode.P100To150;

import java.util.HashSet;
import java.util.Set;

public class Q141 {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {

        Set<ListNode> tp = new HashSet<>();
        while (head!=null){
            if (tp.contains(head)){
                return true;
            }
            tp.add(head);
            head = head.next;
        }
        return false;
    }


    public static void main(String[] args) {


    }
}
