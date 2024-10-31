package com.will.leetcode.P200To249;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i=0;i<prerequisites.length;i++){
            ArrayList<Integer> lists = mp.getOrDefault(prerequisites[i][0], new ArrayList<>());
            lists.add(prerequisites[i][1]);
            mp.put(prerequisites[i][0],lists);
        }
        int[] courses = new int[numCourses];
        int[] visit = new int[numCourses];
        int[] res = new int[numCourses];
        int resIndex = 0;
        for (int i=0;i<numCourses;i++){
            visit[i]=1;
            if (courses[i]==1){
                continue;
            }
            int ch = digui(courses,visit,mp,i,res,resIndex);
            if (ch==0){
                return new int[0];
            }
            courses[i]=1;
            resIndex+=ch;
        }
        return res;
    }

    public static void main(String[] args) {
        Q210 q = new Q210();
        System.out.println(Arrays.toString(q.findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(q.findOrder(4, new int[][]{{1, 0}, {2, 0},{3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(q.findOrder(1, new int[][]{})));
        System.out.println(Arrays.toString(q.findOrder(2, new int[][]{{0,1}})));
        System.out.println(Arrays.toString(q.findOrder(3, new int[][]{{0,1},{0,2}})));
        System.out.println(Arrays.toString(q.findOrder(4,new int[][]{{0,1},{1,2},{0,3},{3,0}})));

    }

    private int digui(int[] courses,int[] visit, Map<Integer, ArrayList<Integer>> mp, int i,int[] res,int resIndex) {
        visit[i]=1;
        if (!mp.containsKey(i)){
            courses[i]=1;
            res[resIndex]=i;
            return 1;
        }
        ArrayList<Integer> needs = mp.get(i);
        int ch=0;
        for (int j=0;j<needs.size();j++){
            Integer index = needs.get(j);
            if (courses[index]==0){
                if (visit[index]==1){
                    return 0;
                }else {
                    int tp = digui(courses,visit,mp,index,res,resIndex+ch);
                    if (tp==0){
                        return 0;
                    }
                    ch+=tp;
                }
            }
        }
        res[resIndex+ch]=i;
        courses[i]=1;
        return ch+1;
    }
}
