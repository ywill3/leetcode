package com.will.leetcode.P50To100;

public class Q86 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(999);
        ListNode big = new ListNode(999);
        ListNode preSmall = small;
        ListNode preBig = big;
        if (head != null) {
            if (head.val < x) {
                small.next = new ListNode(head.val);
                preSmall = preSmall.next;
            } else {
                big.next = new ListNode(head.val);
                preBig = preBig.next;
            }
            head=head.next;
        }
        while (head != null){
            if(head.val<x){
                preSmall.next=new ListNode(head.val);
                preSmall = preSmall.next ;
            }else {
                preBig.next=new ListNode(head.val);
                preBig = preBig.next;
            }
            head = head.next;
        }
        preSmall.next = big.next;
        return small.next;

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        node1.next = node2;
       /* ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(2);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(2);
        node5.next = node6;*/

        ListNode head = partition(node1, 2);
        while (head!=null){
            System.out.print(head.val+" -> ");
            head=head.next;
        }
    }
}
