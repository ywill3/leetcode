package com.will.leetcode.P200To249;

public class Q209 {
    public static void main(String[] args) {
        Q209 q = new Q209();
        System.out.println(q.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(q.minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(q.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
        System.out.println(q.minSubArrayLen(11, new int[]{1,2,3,4,5}));
        System.out.println(q.minSubArrayLen(15, new int[]{1,2,3,4,5}));
        System.out.println(q.minSubArrayLen(15, new int[]{5,1,3,5,10,7,4,9,2,8}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left=0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (right<=nums.length){
            if (res<right-left){
                sum-=nums[left++];
            }
            if (sum<target){
                if (right==nums.length){
                    break;
                }
                sum+=nums[right++];
            }else {
                res = Math.min(res,right-left);
                sum-=nums[left++];
            }
        }
        if (res==Integer.MAX_VALUE){
            return 0;
        }
        return res;
    }
}
