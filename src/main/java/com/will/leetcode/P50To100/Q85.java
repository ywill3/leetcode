package com.will.leetcode.P50To100;

import java.util.Stack;

public class Q85 {

    public static void main(String[] args) {
        char[][] matrix  = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        int x=matrix.length;
        int y=matrix[0].length;
        int[][] heightss = new int[x+1][y];
        for (int i=0;i<y;i++){
            heightss[0][i]=0;
        }
        for (int i=1;i<=x;i++){
            for(int j=0;j<y;j++){
                if(matrix[i-1][j]=='1'){
                    heightss[i][j]=heightss[i-1][j]+1;
                }else {
                    heightss[i][j] = 0;
                }
            }
        }
/*        for(int i=1;i<=x;i++){
            for(int j=0;j<y;j++){
                System.out.print(heightss[i][j]+" ");
            }
            System.out.println();
        }*/

        int ans = 0;
        for (int k=1;k<=x;k++){
            int[] heights = heightss[k];
            int res = 0;
            Stack<Integer> st = new Stack<Integer>();
            for(int i=0;i<=heights.length;i++){
                int h=(i==heights.length)?0:heights[i];
                if(st.isEmpty() || heights[st.peek()]<=h){
                    st.push(i);
                }else{
                    int top = st.pop();
                    res = Math.max(res,heights[top]*(st.isEmpty()?i:(i-st.peek()-1)));
                    i--;
                }
            }
            ans = Math.max(ans,res);
        }
        return ans;
    }
}
