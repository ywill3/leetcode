package com.will.leetcode.P150To200;

public class Q189 {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0 || nums.length == 1) {
            return;
        }
        int[] temp = new int[k];
        int j = 0;
        for (int i = nums.length - 1; i>nums.length-k-1; i--) {
            temp[j++] = nums[i];
        }
        for (int i=nums.length-1;i>k-1;i--){
            nums[i] = nums[i-k];
        }
        for (int i=0;i<k;i++){
            nums[i] = temp[k-i-1];
        }

    }
    public static void main(String[] args) {
        Q189 q = new Q189();
        q.rotate(new int[]{1,2,3,4,5,6,7},3);
    }
}
