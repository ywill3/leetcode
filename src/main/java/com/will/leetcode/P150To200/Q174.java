package com.will.leetcode.P150To200;

import java.util.Arrays;

public class Q174 {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] left = new int[m][n];
        int[][] debt = new int[m][n];
        for (int i=0;i<m;i++){
            Arrays.fill(left[i],0);
            Arrays.fill(debt[i],Integer.MAX_VALUE);
        }
        debt[0][0] = dungeon[0][0]>0?0:1-dungeon[0][0];
        left[0][0] = dungeon[0][0]>0?dungeon[0][0]:1;
        bfs(dungeon,left,debt,0,0);
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                System.out.print(debt[i][j]+" ");
            }
            System.out.println();
        }
        return debt[m-1][n-1];
    }

    private void bfs(int[][] dungeon, int[][] left,int[][] debt, int x, int y) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        if (x==m-1&&y==n-1){
            return;
        }
        // 向右走
        if (y+1<n){
            if (debt[x][y+1]>debt[x][y]+dungeon[x][y+1]){
                debt[x][y+1] = debt[x][y]+dungeon[x][y+1];
                bfs(dungeon,left,debt,x,y+1);
            }
        }
        // 向下走
        if (x+1<m){
        }
    }

    public static void main(String[] args) {
        Q174 q = new Q174();
        System.out.println(q.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));

    }
}
