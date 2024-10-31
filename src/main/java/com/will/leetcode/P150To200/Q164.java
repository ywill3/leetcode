package com.will.leetcode.P150To200;

import java.util.Arrays;

public class Q164 {

    public int maximumGap(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        int res = 0;
        Arrays.sort(nums);
        int pre = nums[0];
        for (int i=1;i<nums.length;i++){
            res = Math.max(res,Math.abs(nums[i]-pre));
            pre = nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Q164 q = new Q164();
        System.out.println(q.maximumGap(new int[]{3,6,9,1})); // 19 / 4 = 4
        System.out.println(q.maximumGap(new int[]{10}));
        System.out.println(q.maximumGap(new int[]{100,1,2,3}));// 106/4 = 26

    }
}
