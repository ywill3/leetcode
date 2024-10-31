package com.will.leetcode.P150To200;

import java.math.BigInteger;

public class Q172 {
    public int trailingZeroes(int n) {
/*        BigInteger res = BigInteger.ONE;
        for (int i=1;i<=n;i++){
            res = res.multiply(BigInteger.valueOf(i));
        }
        String ss = res.toString();
        for (int i=ss.length()-1;i>=0;i--){
            if (ss.charAt(i)!='0'){
                return ss.length()-1-i;
            }
        }
        return 0;*/
        int res = 0;
        while (n>0){
            n = n/5;
            res+=n;
        }
        return res;
    }

    public static void main(String[] args) {
        Q172 q = new Q172();
//        System.out.println(q.trailingZeroes(3));
//        System.out.println(q.trailingZeroes(5));
//        System.out.println(q.trailingZeroes(0));
//        System.out.println(q.trailingZeroes(10000));
        for (int i=1;i<=1000;i++){
            System.out.println(i+":"+q.trailingZeroes(i));
        }
    }
}
