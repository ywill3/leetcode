package com.will.leetcode.P100To150;

public class Q135 {

    public static void main(String[] args) {
        Q135 q135 = new Q135();
        int[] ratings = {1,2,2};//4
//        int[] ratings = {1,0,2};//5
//        int[] ratings = {1,3,2,2,1};//7
//        int[] ratings = {1,2,87,87,87,2,1};//13
//        int[] ratings = {1,6,10,8,7,3,2};//18
//        int[] ratings = {1,2,3,4,5,6,7,8,9};//45
//        int[] ratings = {0,1,2,5,3,2,7};//15
//        int[] ratings = {1,2,3,5,4,3,2,1,4,3,2,1};//31
        System.out.println(q135.candy(ratings));
    }

    public int candy(int[] ratings) {
        if (ratings.length==0 || ratings.length==1){
            return ratings.length;
        }
        int res = 1;
        int last = 1;
        int tp = 1;
        int pre = 1;
        for (int i=1;i<ratings.length;i++){
            if (ratings[i]>ratings[i-1]){
                res+=pre+1;
                tp=1;
                pre+=1;
                last=pre;
            }else if (ratings[i]<ratings[i-1]) {
                if(tp>=last){
                    res+=1;
                }
                tp+=1;
                res+=tp-1;
                pre=1;
            }else {
                tp=1;
                res+=1;
                last=1;
                pre=1;
            }
        }
        return res;
    }
}
