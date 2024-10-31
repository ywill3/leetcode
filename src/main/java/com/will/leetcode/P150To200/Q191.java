package com.will.leetcode.P150To200;

public class Q191 {
    public static void main(String[] args) {
        Q191 q  = new Q191();
        System.out.println(q.hammingWeight(11));
        System.out.println(q.hammingWeight(128));
        System.out.println(q.hammingWeight(2147483645));
    }
    public int hammingWeight(int n) {
        int res = 0;
        while (n!=0){
            if((n&1)==1){
                res++;
            }
            n = n>>1;
        }
        return res;
    }
}
