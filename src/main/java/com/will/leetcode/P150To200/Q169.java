package com.will.leetcode.P150To200;

import java.util.HashMap;
import java.util.Map;

public class Q169 {

    public int majorityElement(int[] nums) {
        Map<Integer,Integer> mp = new HashMap<>();
        for (int num : nums) {
            mp.put(num,mp.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue()>nums.length/2){
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q169 q = new Q169();
        System.out.println(q.majorityElement(new int[]{3,2,3}));
        System.out.println(q.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
