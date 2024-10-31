package com.will.leetcode.P150To200;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Q150 {

    public static void main(String[] args) {
        Q150 q150 = new Q150();
        System.out.println(q150.evalRPN(new String[]{"2","1","+","3","*"}));
        System.out.println(q150.evalRPN(new String[]{"4","13","5","/","+"}));

    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        List<String> operators = Arrays.asList("+","-","*","/");
        for (String token:tokens){
            if (operators.contains(token)){
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(evalCalculate(left,right,token));
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private int evalCalculate(int left,int right,String op){
        switch (op){
            case "+":
                return left+right;
            case "-":
                return left-right;
            case "*":
                return left*right;
            case "/":
                return left/right;
        }
        return 0;
    }
}
