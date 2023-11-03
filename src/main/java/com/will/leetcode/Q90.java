package com.will.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90 {
    public static void main(String[] args) {
        int nums[] = new int[]{1,2,2};
        List<List<Integer>> lists = subsetsWithDup(nums);
        for (int i=0;i<lists.size();i++){
            for (int j=0;j<lists.get(i).size();j++){
                System.out.print(lists.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        digui(0,nums,res,new ArrayList<Integer>());
        return res;
    }

    private static void digui(int index, int[] nums, List<List<Integer>> res,List<Integer> tis) {
        if (index>=nums.length) {
            if (!res.contains(new ArrayList<Integer>(tis))) {
                res.add(new ArrayList<Integer>(tis));
            }
            return;
        }
        tis.add(nums[index]);
        digui(index+1,nums,res,tis);
        tis.remove(tis.size()-1);
        digui(index+1,nums,res,tis);
    }
}
