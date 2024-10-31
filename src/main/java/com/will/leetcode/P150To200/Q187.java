package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for (int i=0;i<s.length()-9;i++){
            String tp = s.substring(i,i+10);
            map.put(tp,map.getOrDefault(tp,0)+1);
        }
        for (String key:map.keySet()){
            if (map.get(key)>1){
                res.add(key);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q187 q = new Q187();
        System.out.println(q.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(q.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(q.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
}
