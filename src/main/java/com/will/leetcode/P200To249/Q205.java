package com.will.leetcode.P200To249;

import java.util.HashMap;
import java.util.Map;

public class Q205 {
    public boolean isIsomorphic(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        if (lent != lens){
            return false;
        }
        Map<Character,Integer> mp = new HashMap<>();
        int cnt = 1;
        Map<Character,Integer> mp2 = new HashMap<>();
        int cnt2 = 1;
        StringBuilder sbs = new StringBuilder();
        StringBuilder sbt = new StringBuilder();
        for (int i=0;i<lens;i++){
            if (mp.containsKey(s.charAt(i))){
                sbs.append(mp.get(s.charAt(i)));
            }
            else {
                mp.put(s.charAt(i),cnt);
                sbs.append(cnt++);
            }

            if (mp2.containsKey(t.charAt(i))){
                sbt.append(mp2.get(t.charAt(i)));
            }
            else {
                mp2.put(t.charAt(i),cnt2);
                sbt.append(cnt2++);
            }
        }
        if (sbt.toString().contentEquals(sbs)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Q205 q = new Q205();
        System.out.println(q.isIsomorphic("egg","add"));
        System.out.println(q.isIsomorphic("foo","bar"));
        System.out.println(q.isIsomorphic("paper","title"));
    }
}
