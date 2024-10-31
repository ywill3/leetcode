package com.will.leetcode.P150To200;

public class Q162 {
    public int findPeakElement(int[] nums) {
        if (nums.length==1){
            return 0;
        }
        for (int i=0;i<nums.length;i++){
            if (i==0){
                if (nums[i]>nums[i+1]){
                    return i;
                }
            }
            if (i==nums.length-1){
                if (nums[i]>nums[i-1]){
                    return i;
                }
            }
            if (nums[i]>nums[i+1] && nums[i]>nums[i-1]){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Q162 q = new Q162();
        System.out.println(q.findPeakElement(new int[]{1,2,3,1}));
        System.out.println(q.findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println(q.findPeakElement(new int[]{1}));
    }
}
