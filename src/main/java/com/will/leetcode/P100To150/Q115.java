package com.will.leetcode.P100To150;

public class Q115 {
    long res = 0;
    public int numDistinct(String s, String t) {
/*        digui(s,t,0,0);
        return (int) (res%1000000007L);*/
        // dp[i][j] 表示 s 的前 i 个字符中选取 t 的前 j 个字符有多少种方案
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[n+1][m+1];
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;
        }
        // 状态转移
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (t.charAt(i-1)==s.charAt(j-1)){
                    dp[i][j]= (dp[i-1][j-1]+dp[i][j-1])%1000000007;
                }else {
                    dp[i][j]= dp[i][j-1];
                }
            }
        }
/*        for (int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/
        return dp[n][m];
    }

    private void digui(String s, String t, int si, int ti) {
        if (ti==t.length()){
            res+=1;
            return;
        }
        if(si==s.length()){
            return;
        }
        if (s.length()-si<t.length()-ti){
            return;
        }
        if(s.charAt(si)==t.charAt(ti)){
            digui(s,t,si+1,ti+1);//take
        }
        digui(s,t,si+1,ti);//not take
    }

    public static void main(String[] args) {
        Q115 q115 = new Q115();
        String s = "babgbag";
        String t = "bag";
        System.out.println(q115.numDistinct(s,t));
    }
}
