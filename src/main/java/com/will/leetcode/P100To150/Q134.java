package com.will.leetcode.P100To150;

public class Q134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int gasSum = 0;
        int costSum = 0;
        for (int i=0;i<len;i++){
            gasSum+=gas[i];
            costSum+=cost[i];
        }
        if (gasSum<costSum){
            return -1;
        }
        int left=0,start=0,remain=0;
        for (int i=0;i<len;i++){
            remain+=gas[i]-cost[i];
            if (remain<0){
                start=i+1;
                left+=remain;
                remain=0;
            }
        }
        if (remain+left<0){
            return -1;
        }
        return start;


        /*for (int i=0;i<len;i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            // 寻找
            int flag = 1;
            int left = 0;
            for (int j = i; j < len; j++) {
                left += gas[j] - cost[j];
                if (left < 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag != 0) {
                for (int j = 0; j < i; j++) {
                    left += gas[j] - cost[j];
                    if (left < 0) {
                        flag = 0;
                        break;
                    }
                }
            }
            if (flag == 1) {
                return i;
            }
        }
        return -1;
        */
//
    }

    public static void main(String[] args) {
        int[] gas = {4,3,10,11};
        int[] cost ={2,10,2,20};
        Q134 q134 = new Q134();
        System.out.println(q134.canCompleteCircuit(gas,cost));
    }
}
