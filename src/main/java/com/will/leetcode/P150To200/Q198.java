package com.will.leetcode.P150To200;

import java.util.Arrays;

public class Q198 {

    public int rob(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp,0);
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int result = Math.max(nums[0],nums[1]);
        dp[0]=nums[0];
        dp[1]=nums[1];
        for (int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
            if (i-3>=0){
                dp[i] = Math.max(dp[i],dp[i-3]+nums[i]);
            }
            result = Math.max(dp[i],result);
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = {2,1};
        System.out.println(new Q198().rob(nums));
    }
}
