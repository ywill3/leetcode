package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q132 {

    int res=Integer.MAX_VALUE;
    public int minCut1(String s) {
        List<String> path=new ArrayList<>();
        digui(s,0,path);
        return res;
    }

    private void digui(String s, int index, List<String> path) {
        if(index>=s.length()){
            res=Math.min(res,path.size()-1);
            return;
        }
        for (int i=s.length()-1;i>=index;i--){
            if (judge(s,index,i)){
                if (path.size()>res) return;
                path.add(s.substring(index,i+1));
                digui(s,i+1,path);
                path.remove(path.size()-1);
            }
        }
    }

    public int minCut(String s) {
        int tp[][] = new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            for (int j=i;j<s.length();j++){
                if (judge(s,i,j)){
                    tp[i][j]=1;
                }
            }
        }
//
//        for (int i=0;i<s.length();i++){
//            for (int j=0;j<s.length();j++){
//                System.out.print(tp[i][j]+" ");
//            }
//            System.out.println();
//        }
        int dp[] = new int[s.length()];
        dp[0]=0;
        for (int i=1;i<s.length();i++){
            dp[i]=Integer.MAX_VALUE;
        }
        for (int i=1;i<s.length();i++){
            int tpRes = Integer.MAX_VALUE;
            for (int j=0;j<i;j++){
                if (tp[j][i]==1){
                    if (j==0){
                        tpRes = 0;
                        break;
                    }else {
                        tpRes = Math.min(tpRes,dp[j-1]+1);
                    }
                }
                if (j==0){
                    tpRes = Math.min(tpRes,i-j);
                }else {
                    tpRes = Math.min(tpRes,dp[j]+i-j);
                }

            }
            dp[i]=tpRes;
        }
//        for (int i=0;i<s.length();i++){
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();

        return dp[s.length()-1];
    }

    private boolean judge(String s, int left, int right){
        while (left<right){
            if (s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Q132 q132 = new Q132();
//        System.out.println(q132.minCut("abbab"));
//        System.out.println(q132.minCut("cbbbcc"));
        // aaabaa
        // ababababababababababababcbabababababababababababa
        // apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp
//        System.out.println(q132.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
    }
}
