package com.will.leetcode.P50To100;

import java.util.Arrays;

public class Q91 {
    public static void main(String[] args) {
        String s = "0226";
        System.out.println(numDecodings(s));
    }

    public static int numDecodings(String s) {

        int n = s.length();
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        return digui(s, 0, mem);
    }

    private static int digui(String s, int ind, int[] mem) {
        int n = s.length();
        if (ind==n)
            return 1;
        if(s.charAt(ind)=='0')
            return 0;
        if(mem[ind]!=-1){
            return mem[ind];
        }
        int count = digui(s, ind+1, mem);
        if(ind < n - 1 && ((s.charAt(ind) - '0') * 10 + (s.charAt(ind + 1) - '0')) < 27){
            count += digui(s, ind+2, mem);
        }
        mem[ind] = count;
        return count;
    }
}
