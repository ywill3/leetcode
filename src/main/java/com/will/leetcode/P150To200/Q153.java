package com.will.leetcode.P150To200;

public class Q153 {
    public int findMin(int[] nums) {
        return digui(nums,0,nums.length-1);
    }

    private int digui(int[] nums,int start, int end){
        if (nums[start]<=nums[end]){
            return nums[start];
        }
        int mind = (start+end)/2;
        if (nums[mind]>nums[end]){
            return digui(nums,mind+1,end);
        }
        else {
            return digui(nums,start,mind);
        }
    }

    public static void main(String[] args) {

        System.out.println(new Q153().findMin(new int[]{3,4,5,1,2}));
        System.out.println(new Q153().findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(new Q153().findMin(new int[]{11,13,15,17}));
        System.out.println(new Q153().findMin(new int[]{2,2,2,0,1}));
        System.out.println(new Q153().findMin(new int[]{1,3,5}));
        System.out.println(new Q153().findMin(new int[]{3,3,1,3,5}));
    }
}
