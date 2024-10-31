package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q148 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        List<Integer> tmpList = new ArrayList<>();
        while (head!=null){
            tmpList.add(head.val);
            head = head.next;
        }
        Integer[] array = tmpList.stream().toArray(Integer[]::new);
        Arrays.sort(array);
        ListNode newHead = new ListNode(-10002);
        ListNode last = newHead;
        for (Integer integer : array) {
            last.next = new ListNode(integer);
            last = last.next;
        }
        return newHead.next;
    }


}
