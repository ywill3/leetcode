package com.will.leetcode.P100To150;

public class Q121 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int[] dp = new int[prices.length];
        for (int i=0;i<prices.length;i++){
            dp[i]=prices[i];
        }
        for (int i=1;i<prices.length;i++){
            dp[i]=Math.min(dp[i-1],dp[i]);
        }
        for (int i=1;i<prices.length;i++){
            res=Math.max(res,prices[i]-dp[i-1]);
        }
        return res;
    }

    public static void main(String[] args) {
        Q121 q121 = new Q121();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(q121.maxProfit(prices));
    }
}
