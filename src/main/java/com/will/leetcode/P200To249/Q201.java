package com.will.leetcode.P200To249;

public class Q201 {

    public static void main(String[] args) {
        Q201 q = new Q201();
//        System.out.println((1<<31)-1-(1<<30));
        System.out.println(q.rangeBitwiseAnd(6,7));
        System.out.println();
    }
    public int rangeBitwiseAnd(int left, int right) {
        if (left==right){
            return left;
        }
        int tp = 1;
        int res = 0;
        for (int i=0;i<31;i++){
            if (left>=tp && left<=(tp<<1)-1)
            {
                if (right>=tp && right<=(tp<<1)-1){
                    res += tp;
                    res += rangeBitwiseAnd(left%tp,right%tp);
                    return res;
                }else {
                    return 0;
                }
            }else {
                tp = tp<<1;
            }
        }
        return res;
    }
}
