package com.will.leetcode;

public class Q82 {
    public static void main(String[] args) {
//        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(1);
//        node0.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(2);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
//        ListNode node5 = new ListNode(4);
//        node4.next = node5;
        ListNode ans = deleteDuplicates(node1);
        while (ans!=null){
            System.out.println(ans.val);
            ans=ans.next;
        }
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(-101);
        preHead.next = head;
        ListNode pre1 = preHead;
        while (head != null){
            if(head.next!=null&&head.val==head.next.val){
                while (head.next!=null&&head.val==head.next.val){
                    head = head.next;
                }
                pre1.next = head.next;
                head = head.next;
            }else{
                pre1 = pre1.next;
                head = head.next;
            }
        }
        return preHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

