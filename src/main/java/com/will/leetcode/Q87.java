package com.will.leetcode;

public class Q87 {
    public static void main(String[] args) {
        String s = "abcde";
        StringBuilder res = new StringBuilder("abc");
        digui(s,res,s.length());
    }

    public static void digui(String s,StringBuilder res,int len){
        if(s.length()==1){
            res.append(s);
            if(res.length()==len){
                System.out.println(res.toString());
            }
            return;
        }
        for(int i=1;i<s.length();i++){
            String s1 = s.substring(0,i);
            String s2 = s.substring(i);
//            for(int j=0;j<2;j++){
                digui(s1,res,len);
                digui(s2,res,len);
                res = new StringBuilder();
//            }
        }
    }

    public boolean isScramble(String s1, String s2) {

        return false;
    }
}
