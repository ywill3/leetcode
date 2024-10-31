package com.will.leetcode.P150To200;

public class Q171 {
    public static void main(String[] args) {
        Q171 q = new Q171();
        System.out.println(q.titleToNumber("A"));
        System.out.println(q.titleToNumber("AB"));
        System.out.println(q.titleToNumber("ZY"));

    }
    public int titleToNumber(String columnTitle) {
        int res=0;
        int tp=0;
        for (int i=columnTitle.length()-1;i>=0;i--){
            res+=(columnTitle.charAt(i)-'A'+1)*Math.pow(26,tp);
            tp++;
        }
        return res;
    }
}
