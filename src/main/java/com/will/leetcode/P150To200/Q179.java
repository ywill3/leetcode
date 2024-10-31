package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q179 {
    public String largestNumber(int[] nums) {
        List<String> tp = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            tp.add(String.valueOf(nums[i]));
        }
        Collections.sort(tp,(a,b)->{
            String s1 = a+b;
            String s2 = b+a;
            return s2.compareTo(s1);
        });
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<tp.size();i++){
            sb.append(tp.get(i));
        }
        return sb.toString().charAt(0)=='0'?"0":sb.toString();
    }

    public static void main(String[] args) {
        Q179 q = new Q179();
        System.out.println(q.largestNumber(new int[]{10,2}));
        System.out.println(q.largestNumber(new int[]{3,30,34,5,9}));
    }
}
