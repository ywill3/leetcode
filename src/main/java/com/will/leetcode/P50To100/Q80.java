package com.will.leetcode.P50To100;

public class Q80 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3,4,4,4,4,4,4,4};
        removeDuplicates(nums);

    }
    public static int removeDuplicates(int[] nums) {
        int ans = 0;
        int j = 1;
        int tmp = nums[0];
        int tmp1 = 1;
        for(int i=1;i<nums.length;i++){
            if (nums[i]==tmp){
                tmp1++;
            }else{
                tmp=nums[i];
                tmp1 = 1;
            }
            if(tmp1>2){
                ans++;
            }else{
                nums[j++]=nums[i];
            }
        }
        return nums.length-ans;
    }
}
