package com.will.leetcode.P150To200;

public class Q168 {
    public static void main(String[] args) {
        Q168 q = new Q168();
        System.out.println(q.convertToTitle(1));
        System.out.println(q.convertToTitle(28));
        System.out.println(q.convertToTitle(701));
        System.out.println(q.convertToTitle(52));
        System.out.println(q.convertToTitle(703));
    }
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber>0){
            int tmp = columnNumber%26;
            columnNumber = columnNumber/26;
            if (tmp==0) {
                sb.append('Z');
                columnNumber--;
            }else {
                sb.append((char)('A'+tmp-1));
            }
        }
        return sb.reverse().toString();
    }
}
