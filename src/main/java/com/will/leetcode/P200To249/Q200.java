package com.will.leetcode.P200To249;

public class Q200 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(new Q200().numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (visited[i][j]==1 || grid[i][j]=='0'){
                    continue;
                }
                if (grid[i][j]=='1'){
                    bfs(grid,visited,i,j);
                    res+=1;
                }
            }
        }
       return res;
    }

    public void bfs(char[][] grid, int[][] visited, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        if (i<0 || i>=m || j<0 || j>=n){
            return;
        }
        if (grid[i][j]=='0' || visited[i][j]==1)
            return;
        visited[i][j]=1;
        bfs(grid,visited,i+1,j);
        bfs(grid,visited,i-1,j);
        bfs(grid,visited,i,j+1);
        bfs(grid,visited,i,j-1);
    }

}
