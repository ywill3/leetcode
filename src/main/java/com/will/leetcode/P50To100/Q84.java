package com.will.leetcode.P50To100;

import java.util.Stack;

public class Q84 {
    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(heights));
    }

    public static int largestRectangleArea1(int[] heights) {

        int res = 0;
        for (int i=0;i<heights.length;i++){
            for(int j=i+1;j<heights.length;j++){
                if(heights[j]<heights[j-1]){
                    i=j-1;
                    break;
                }else {
                    i=j;
                }
            }
            int lheight = heights[i];
            for(int j=i;j>=0;j--){
                if(lheight>heights[j]){
                    lheight=heights[j];
                }
                int area = lheight*(i-j+1);
                res = Math.max(res,area);
            }
        }
        return res;
    }

    public static int largestRectangleArea(int[] heights) {
        int i = 0;
        Stack<Integer> st = new Stack<Integer>();
        int res = 0;
        while (i<heights.length){
            if(st.isEmpty() || heights[st.peek()]<=heights[i]){
                st.push(i++);
            }else {
                int j = st.pop();
                res = Math.max(res,heights[j]*(st.isEmpty()?i:i-st.peek()-1));
            }
        }
        /*if(res == 0){
            for(int j=0;j<heights.length;j++){
                res = Math.max(res,heights[j]*(heights.length-j));
            }
        }*/
        return res;
    }

    public static int largestRectangleArea2(int[] heights) {
        Stack<Integer> st = new Stack<Integer>();
        int res = 0;
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
        return res;
    }
}
