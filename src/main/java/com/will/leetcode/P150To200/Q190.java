package com.will.leetcode.P150To200;

public class Q190 {
    public static void main(String[] args) {
        System.out.println(new Q190().reverseBits(43261596));
    }

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res += n & 1;
            n = n >> 1;
        }
        return res;
    }
}
