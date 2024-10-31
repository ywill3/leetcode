package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        dp[0][0]=triangle.get(0).get(0);
        int size = triangle.size();
        for (int i=1;i<size;i++){
            for (int j=0;j<=i;j++){
                int tmp = Integer.MAX_VALUE;
                if (j-1>=0){
                    tmp=Math.min(tmp,dp[i-1][j-1]+triangle.get(i).get(j));
                }
                if (j<i) {
                    tmp=Math.min(tmp,dp[i-1][j]+triangle.get(i).get(j));
                }
                dp[i][j]=tmp;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i=0;i<triangle.size();i++){
            res = Math.min(res,dp[size-1][i]);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(List.of(2)));
        triangle.add(new ArrayList<>(List.of(3,4)));
        triangle.add(new ArrayList<>(List.of(6,5,7)));
        triangle.add(new ArrayList<>(List.of(4,1,8,3)));
        Q120 q120 = new Q120();
        System.out.println(q120.minimumTotal(triangle));
    }
}
