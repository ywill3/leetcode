package com.will.leetcode.P100To150;

public class Q122 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int[] dp = new int[prices.length];
        for (int i=0;i<prices.length;i++){
            dp[i]=prices[i];
        }
        for (int i=1;i<prices.length;i++){
            if (prices[i]<prices[i-1]){
                dp[i]=prices[i];
                continue;
            }
            dp[i]=Math.min(dp[i-1],dp[i]);
        }
        int tmp = 0;
        for (int i=1;i<prices.length;i++){
            if (prices[i]<prices[i-1]){
                res+=tmp;
                tmp=0;
                continue;
            }
            tmp=Math.max(tmp,prices[i]-dp[i-1]);
        }
        return res+tmp;
    }

    public static void main(String[] args) {
        Q122 q122 = new Q122();
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(q122.maxProfit(prices));
    }
}
