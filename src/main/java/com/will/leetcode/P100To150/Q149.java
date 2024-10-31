package com.will.leetcode.P100To150;

import java.util.HashMap;
import java.util.Map;

public class Q149 {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1,1},{3,2}, {5,3},{4,1},{2,3},{1,4}};
        System.out.println(new Q149().maxPoints(points));
    }


    public int maxPoints(int[][] points) {
        if (points.length<=2){
            return points.length;
        }
        int res = 1;
        for (int i=0;i<points.length;i++){
            Map<Double,Integer> mp = new HashMap<>();
            for (int j=0;j<points.length;j++){
                if (i==j){
                    continue;
                }
                double xl = (points[i][0] - points[j][0])*1.0 / (points[i][1] - points[j][1]);
                Integer cnt = mp.getOrDefault(xl, 1);
                mp.put(xl,cnt+1);
            }
            for (Double key:mp.keySet()){
                if (mp.get(key)>res)
                    res = mp.get(key);
            }
        }
        return res;
    }
}
