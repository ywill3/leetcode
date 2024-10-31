package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q140 {
    int dp[] = new int[1000];
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        digui(s,0,wordDict,res,new StringBuilder());
        return res;
    }

    private boolean digui(String s, int index, List<String> wordDict, List<String> res,StringBuilder tmp) {
        if (index>=s.length()){
            res.add(tmp.toString().trim());
            return true;
        }
        for (int i=index;i<=s.length();i++){
            if (wordDict.contains(s.substring(index,i))){
//                if (dp[i]==1){
//                    continue;
//                }
                tmp.append(s, index, i).append(" ");
                boolean b = digui(s,i,wordDict,res,tmp);
//                if (!b){
//                    dp[i]=1;
//                }
                tmp.delete(tmp.length()-(i-index+1),tmp.length());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q140 q = new Q140();
        String s = "catsandog";
        List<String> wordDict = List.of("cats","dog","sand","and","cat");
        List<String> strings = q.wordBreak(s, wordDict);
        System.out.println(strings);
    }
}
