package com.will.leetcode.P100To150;

import java.util.List;

public class Q147 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = new ListNode(-5002);
        while (head!=null){
            ListNode next = new ListNode(head.val);
            ListNode tmpHead = newHead;
            while (tmpHead.next!=null && tmpHead.next.val<head.val){
                tmpHead = tmpHead.next;
            }
            ListNode tmp = tmpHead.next;
            tmpHead.next = next;
            next.next = tmp;
            head = head.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {

    }
}
