package com.will.leetcode.P150To200;

import java.util.Stack;

public class Q151 {

    public static void main(String[] args) {

        String s = "the sky is blue";
        Q151 q151 = new Q151();
        System.out.println(q151.reverseWords(s));
    }


    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)==' '){
                if (sb.length()!=0){
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        if (sb.length()!=0){
            stack.push(sb.toString());
        }
        sb = new StringBuilder();
        int size = stack.size();
        for (int i=0;i<size;i++){
            sb.append(stack.pop());
            if (i!=size-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

