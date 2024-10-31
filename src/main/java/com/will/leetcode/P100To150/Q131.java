package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q131 {
    public static void main(String[] args) {
        Q131 q131 = new Q131();
        List<List<String>> aab = q131.partition("aab");
        for (int i=0;i<aab.size();i++){
            for (int j=0;j<aab.get(i).size();j++){
                System.out.print(aab.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tis = new ArrayList<>();
        digui(s,0,tis,res);
        return res;
    }

    private void digui(String s, int index, List<String> tis, List<List<String>> res) {
        if (index>=s.length()){
            res.add(new ArrayList<>(tis));
            return;
        }
        for (int i=index;i<s.length();i++){
            if (judge(s,index,i)){
                tis.add(s.substring(index,i+1));
                digui(s,i+1,tis,res);
                tis.remove(tis.size()-1);
            }
        }
    }

    private boolean judge(String s, int left, int right){
        while (left<right){
            if (s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
