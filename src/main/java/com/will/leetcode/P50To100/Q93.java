package com.will.leetcode.P50To100;

import java.util.ArrayList;
import java.util.List;

public class Q93 {

    public static void main(String[] args) {
        String s = "000";
        List<String> strings = restoreIpAddresses(s);
        for (int i=0;i<strings.size();i++){
            System.out.println(strings.get(i));
        }
//        System.out.println(judge("1010"));
/*        String[] split = "1".split("\\.");
        System.out.println(split.length);*/
    }

    public static List<String> restoreIpAddresses(String s) {

        List<String> ans = new ArrayList<String>();
        digui(s,0,ans,"");
        return ans;
    }

    private static void  digui(String s, int index, List<String> ans, String ts) {
        String[] split = ts.split("\\.");
        String las = split[split.length-1];
        if(!judge(las)) return;
        if (index==s.length())
        {
            if (split.length==4){
                ans.add(ts);
            }
            return;
        }

//        las+=s.charAt(index);
        if("".equals(las)){
            digui(s,index+1,ans,ts+s.charAt(index));
        }else{
            digui(s,index+1,ans,ts+s.charAt(index));
            digui(s,index+1,ans,ts+"."+s.charAt(index));
        }
    }

    private static boolean judge(String las) {
        if(las.length()>1 && las.charAt(0)=='0') return false;
        int ans = 0;
        for(int i=0;i<las.length();i++){
            ans+=(las.charAt(i)-'0')*Math.pow(10,las.length()-i-1);
        }
        if (ans>=0 && ans<=255) return true;
        else return false;
    }
}
