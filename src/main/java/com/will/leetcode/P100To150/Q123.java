package com.will.leetcode.P100To150;

public class Q123 {

    public int maxProfit(int[] prices) {
/*        int dp[][] = new int[prices.length][3];
        for (int i=0;i<prices.length;i++){
            dp[i][0]=0;
        }
        for (int i=1;i<prices.length;i++){
         for (int j=0;j<i;j++){
             for (int k=1;k<=2;k++){
                 dp[i][k]=Math.max(dp[i][k],Math.max(dp[i-1][k],dp[j][k-1]+prices[i]-prices[j]));
             }
         }
        }
        return dp[prices.length-1][2];*/

        /*if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[][] dp = new int[prices.length][K + 1];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                //找出第 1 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                min = Math.min(prices[i] - dp[i][k - 1], min);
                //比较不操作和选择一天买入的哪个值更大
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[prices.length - 1][K];*/

        // 状态机解法
        if(prices.length==0) return 0;
        //进行初始化，第一天 s1 将股票买入，其他状态全部初始化为最小值
        int s1=-prices[0],s2=Integer.MIN_VALUE,s3=Integer.MIN_VALUE,s4=Integer.MIN_VALUE;

        for(int i=1;i<prices.length;++i) {
            s1 = Math.max(s1, -prices[i]); //买入价格更低的股
            s2 = Math.max(s2, s1+prices[i]); //卖出当前股，或者不操作
            s3 = Math.max(s3, s2-prices[i]); //第二次买入，或者不操作
            s4 = Math.max(s4, s3+prices[i]); //第二次卖出，或者不操作
        }
        return Math.max(0,s4);
    }

    public static void main(String[] args) {
        Q123 q123 = new Q123();
        int[] prices = {3,8,4,9,0,100};
        System.out.println(q123.maxProfit(prices));
    }
}
