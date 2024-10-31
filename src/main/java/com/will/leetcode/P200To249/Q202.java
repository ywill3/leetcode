package com.will.leetcode.P200To249;

import java.util.ArrayList;
import java.util.List;

public class Q202 {
    public static void main(String[] args) {
        Q202 q = new Q202();
        for (int i=1;i<200;i++){
            if (q.isHappy(i)){
                System.out.println(i);
            }
        }
    }
    public boolean isHappy(int n) {
        List<Integer> tp = new ArrayList<>();
        while (n!=1){
            tp.add(n);
            int res=0;
            while (n!=0){
                res += (n%10)*(n%10);
                n=n/10;
            }
            if (tp.contains(res) && res!=1){
                return false;
            }
            n=res;
//            System.out.println(n);
        }
        return true;
    }
}
