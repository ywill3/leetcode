package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q89 {
    public static void main(String[] args) {
        List<Integer> integers = grayCode(3);
        for (int i=0;i<integers.size();i++){
            System.out.print(integers.get(i)+" ");
        }
    }
    public static List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        digui(n,0,res);
        return res;
    }

    public static void digui(int n,int ind,List<Integer> res){
//        if(flag==1) return;
        if(ind==Math.pow(2,n)-1){
            if(judge(res.get(ind),res.get(0),n)){
                for (int i=0;i<Math.pow(2,n);i++){
                    System.out.print(res.get(i)+" ");
                }
                System.out.println();
//                flag=1;
            }
            return;
        }
        for(int i=1;i<Math.pow(2,n);i++){
            if(!res.contains(i) && judge(res.get(ind),i,n)){
                res.add(i);
                digui(n,ind+1,res);
//                if (flag==1) return;
                res.remove(ind+1);
            }
        }
    }
    public static boolean judge(int a,int b,int n){
        String aa = trans(a,n);
        String bb = trans(b,n);
//        System.out.println(aa+"---"+bb);
        int cy = 0;
        for(int i=0;i<n;i++){
            if(aa.charAt(i)!=bb.charAt(i)){
                cy++;
            }
        }
        if(cy==1){
            return true;
        }else {
            return false;
        }
    }

    public static String trans(int Scanner,int n){
        String base="";
        int sys=0;
        while(true){
            sys=Scanner%2;
            Scanner=Scanner/2;
            base=sys+base;
            if(Scanner<2){
                sys=Scanner%2;
                base=sys+base;
                break;
            }
        }
        int x = base.length();
        for(int i=0;i<n-x;i++){
            base="0"+base;
        }
        return base;
    }
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        int head = 1;
        for(int i=1;i<=n;i++){
            for(int j=res.size()-1;j>=0;j--){
                res.add(head+res.get(j));
            }
            head= head*2;
        }
        return res;
    }

}
