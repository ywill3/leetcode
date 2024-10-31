package com.will.leetcode.P100To150;

import java.util.HashSet;
import java.util.Set;

public class Q142 {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> tp = new HashSet<>();
        while (head!=null){
            if (tp.contains(head)){
                return head;
            }
            tp.add(head);
            head = head.next;
        }
        return null;
    }
    public static void main(String[] args) {


    }
}
