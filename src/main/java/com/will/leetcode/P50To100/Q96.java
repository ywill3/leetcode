package com.will.leetcode.P50To100;

public class Q96 {

    public static int numTrees(int n) {
        if(n<=1) return 1;
        int[] res = new int[n+1];
        res[0]=1;
        res[1]=1;
        for (int i=2;i<=n;i++){
            for (int j=1;j<=i;j++){
                res[i]+=res[j-1]*res[i-j];
            }
        }
        return res[n];
    }

    public static void main(String[] args) {
        numTrees(10);
    }
}
