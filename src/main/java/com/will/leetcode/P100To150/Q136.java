package com.will.leetcode.P100To150;

import java.util.HashMap;
import java.util.Map;

public class Q136 {
    public static void main(String[] args) {
        Q136 q136 = new Q136();
        int[] nums = {2,2,1};
        System.out.println(q136.singleNumber(nums));
    }

    public int singleNumber1(int[] nums) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else {
                map.put(nums[i],1);
            }
        }
        if (map.keySet().size()==1){
            return (int)map.keySet().toArray()[0];
        }
        return -1;
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i=0;i<nums.length;i++){
            res^=nums[i];
        }
        return res;
    }
}
