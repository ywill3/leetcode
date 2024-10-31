package com.will.leetcode.P50To100;

public class Q97 {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length())
            return false;
        if (s1.length()==0)
            return s2.equals(s3);
        if (s2.length()==0)
            return s1.equals(s3);
        int[] tp1 = new int[27];
        int[] tp2 = new int[27];
        for (int i=0;i<=25;i++){
            tp1[i]=0;
            tp2[i]=0;
        }
        for(int i=0;i<s1.length();i++){
            tp1[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            tp1[s2.charAt(i)-'a']++;
        }
        for(int i=0;i<s3.length();i++){
            tp2[s3.charAt(i)-'a']++;
        }

        for (int i=0;i<=25;i++){
            if (tp1[i]!=tp2[i])
                return false;
        }
        return digui(s1,0,s2,0,s3,0,false);
    }

    private static boolean digui(String s1, int s1Index, String s2, int s2Index, String s3, int s3Index,boolean res) {
        if(s1.length()-1==s1Index && s2.length()==s2Index){
            if (s1.charAt(s1Index)==s3.charAt(s3Index))
                return true;
            else return false;
        }if(s1.length()==s1Index && s2.length()-1==s2Index){
            if (s2.charAt(s2Index)==s3.charAt(s3Index))
                return true;
            else return false;
        }
        if (res){
            return true;
        }
        if(s1Index<s1.length() && s1.charAt(s1Index)==s3.charAt(s3Index)){
            boolean rr = digui(s1, s1Index + 1, s2, s2Index, s3, s3Index + 1, res);
            if (rr)
                return true;
        }

        if(s2Index<s2.length() && s2.charAt(s2Index)==s3.charAt(s3Index)){
            boolean rr = digui(s1,s1Index,s2,s2Index+1,s3,s3Index+1,res);
            if (rr)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "abababababababababababababababababababababababababababababababababababababababababababababababababbb";
        String s2 = "babababababababababababababababababababababababababababababababababababababababababababababababaaaba";
//        String s3 = "aadbbcbcac";
        String s3 = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababbb";
        System.out.println(isInterleave(s1,s2,s3));
    }
}
