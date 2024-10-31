package com.will.leetcode.P100To150;

import java.util.List;

public class Q139 {
    int dp[] = new int[1000];
    public boolean wordBreak(String s, List<String> wordDict) {

        return digui(s,0,wordDict);
    }

    private boolean digui(String s, int index, List<String> wordDict) {
        if (index>=s.length()){
            return true;
        }
        for (int i=index;i<=s.length();i++){
            if (wordDict.contains(s.substring(index,i))){
                if (dp[i]==1){
                    continue;
                }
                boolean b = digui(s,i,wordDict);
                if (b){
                    return true;
                }else {
                    dp[i]=1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        Q139 q139 = new Q139();
        System.out.println(q139.wordBreak(s,wordDict));

    }
}
