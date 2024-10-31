package com.will.leetcode.P150To200;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q160 {

    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*Map<ListNode,Boolean> map = new HashMap<>();
        while (headA!=null){
            map.put(headA,true);
            headA = headA.next;
        }
        while (headB!=null){
            if (map.getOrDefault(headB,false)){
                return headB;
            }
            headB = headB.next;
        }
        return null;*/
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;
        while (a!=b){
            a = a==null?headB:a.next;
            b = b==null?headA:b.next;
        }
        return a;

    }
}
