package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;
public class Q118 {

    public List<List<Integer>> generate(int numRows) {
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
        return res;
    }

    public static void main(String[] args) {
        Q118 q118 = new Q118();
        System.out.println(q118.generate(30));
    }
}
