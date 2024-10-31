package com.will.leetcode.P100To150;

public class Q137 {

    public int singleNumber(int[] nums) {
        int one=0;
        int two=0;
        for (int i=0;i<nums.length;i++){
            one = one ^ nums[i] & ~two;
            two = two ^ nums[i] & ~one;
        }
        return one;
    }

    public static void main(String[] args) {
        Q137 q137 = new Q137();
        System.out.println(q137.singleNumber( new int[]{2,2,3,2}));
    }
}
