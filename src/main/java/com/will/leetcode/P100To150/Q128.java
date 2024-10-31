package com.will.leetcode.P100To150;

import java.util.HashSet;

public class Q128 {

    public static void main(String[] args) {
        int[] nums = {};
        Q128 q128 = new Q128();
        System.out.println(q128.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for(int num:nums){
            if (!set.contains(num-1)){
                int tmp = 1;
                while (set.contains(num+1)){
                    tmp++;
                    num++;
                }
                res = Math.max(res,tmp);
            }
            if (res>=nums.length/2){
                return res;
            }
        }
        return res;
    }
}
