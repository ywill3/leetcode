package com.will.leetcode.P100To150;

public class Q125 {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()){
            return true;
        }
        // 删除特殊字符
        s = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        System.out.println(s);
        for (int i=0,j=s.length()-1;i<j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q125 q125 = new Q125();
        System.out.println(q125.isPalindrome(" "));
    }
}
