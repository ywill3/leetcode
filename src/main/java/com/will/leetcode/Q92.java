package com.will.leetcode;



public class Q92 {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dum = new ListNode(-1);
        dum.next=head;
        ListNode pre = head;
        ListNode q = dum;
        for(int i=0;i<left-1;i++){
            q = q.next;
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i=0;i<right-left;i++){
            pre.next = cur.next;
            cur.next = q.next;
            q.next = cur;
            cur = pre.next;
        }


        return dum.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
/*        ListNode node6 = new ListNode(2);
        node5.next = node6;*/
        ListNode listNode = reverseBetween(node1, 2, 4);
        while (listNode.next!=null){
            System.out.print(listNode.val+" ");
            listNode = listNode.next;
        }
        System.out.println(listNode.val);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
