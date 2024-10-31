package com.will.leetcode.P150To200;

import java.util.Arrays;

public class Q152 {
    public static void main(String[] args) {

        int nums[] = {-3,-1,0,-2};
        System.out.println(new Q152().maxProduct(nums));
//        System.out.println(Integer.MAX_VALUE);
//        int res = 1;
//        for (int i=0;i<nums.length;i++){
//            res = res*nums[i];
//            System.out.println(res);
//        }
    }

    public int maxProduct(int[] nums) {
       /* int res = Integer.MIN_VALUE;
        int flag = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                res = Math.max(0,res);
                res = Math.max(res,digui(Arrays.copyOfRange(nums,flag,i)));
                flag = i+1;
            }
        }
        if (flag<nums.length){
            res = Math.max(res,digui(Arrays.copyOfRange(nums,flag,nums.length)));
        }
        return res;*/

        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;  // store the max because before updating min your max will already be updated
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > ans) {
                ans = max;
            }
        }
        return ans;
    }

    private int digui(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int countNeg = 0;
        int lastNeg = -1;
        int firstNeg = -1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]<0){
                countNeg++;
                if (firstNeg==-1){
                    firstNeg = i;
                }
                lastNeg = i;
            }
        }
        if (countNeg%2==0){
            int res = 1;
            for (int i=0;i<nums.length;i++){
                res = res*nums[i];
            }
            return res;
        }else {
            int res = Integer.MIN_VALUE;
            int res1 = 1;
            for (int i=0;i<firstNeg;i++){
                res1 = res1*nums[i];
            }
            res = Math.max(res,res1);
            int res2 = 1;
            for (int i=firstNeg+1;i<nums.length;i++){
                res2 = res2*nums[i];
            }
            res = Math.max(res,res2);
            if (countNeg==1){
                return res;
            }else {
                int res3 = 1;
                for (int i=0;i<lastNeg;i++){
                    res3 = res3*nums[i];
                }
                res = Math.max(res,res3);
                int res4 = 1;
                for (int i=lastNeg+1;i<nums.length;i++){
                    res4 = res4*nums[i];
                }
                res = Math.max(res,res4);
                return res;
            }

        }
    }
}
