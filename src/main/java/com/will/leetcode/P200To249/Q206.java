package com.will.leetcode.P200To249;

import java.util.ArrayList;
import java.util.List;

public class Q206 {
    public ListNode reverseList(ListNode head) {

        List<Integer> tp = new ArrayList<>();
        while (head!=null){
            tp.add(head.val);
            head=head.next;
        }
        ListNode res = new ListNode(-1);
        ListNode tpRes = res;
        for (int i=tp.size()-1;i>=0;i--){
            tpRes.next = new ListNode(tp.get(i));
            tpRes = tpRes.next;
        }
        return res.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

    }
}
