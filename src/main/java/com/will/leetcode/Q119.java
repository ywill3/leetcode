package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q119 {
    public List<Integer> generate(int numRows) {
        numRows+=1;
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> last = res.get(i-1);
            ArrayList<Integer> lt = new ArrayList<>();
            for (int j=0;j<=i;j++){
                if(j==0 || j==i){
                    lt.add(1);
                    continue;
                }
                lt.add(last.get(j)+last.get(j-1));
            }
            res.add(lt);
        }
        return res.get(numRows-1);
    }

    public static void main(String[] args) {
        Q119 q119 = new Q119();
        System.out.println(q119.generate(3));
    }
}
