package com.will.leetcode.P200To249;

public class Q203 {
    public static void main(String[] args) {

    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head==null){
            return null;
        }
        ListNode newR = new ListNode(-1);
        ListNode tpR = newR;
        while (head!=null){
            if (head.val!=val){
                tpR.next=new ListNode(head.val);
                tpR = tpR.next;
            }
            head=head.next;
        }
        return newR.next;
    }
}
