package com.will.leetcode.P200To249;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Q207 {
    public static void main(String[] args) {
        Q207 q = new Q207();
        System.out.println(q.canFinish(2,new int[][]{{1,0}}));
        System.out.println(q.canFinish(2,new int[][]{{1,0},{0,1}}));
        System.out.println(q.canFinish(3,new int[][]{{1,0},{1,2},{0,1}}));
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i=0;i<prerequisites.length;i++){
            ArrayList<Integer> lists = mp.getOrDefault(prerequisites[i][0], new ArrayList<>());
            lists.add(prerequisites[i][1]);
            mp.put(prerequisites[i][0],lists);
        }
        int[] courses = new int[numCourses];
        int[] visit = new int[numCourses];
        for (int i=0;i<numCourses;i++){
            visit[i]=1;
            if (courses[i]==1){
                continue;
            }
            if (!digui(courses,visit,mp,i)){
                return false;
            }
            courses[i]=1;
        }
        return true;
    }

    private boolean digui(int[] courses,int[] visit, Map<Integer, ArrayList<Integer>> mp, int i) {
        visit[i]=1;
        if (!mp.containsKey(i)){
            courses[i]=1;
            return true;
        }
        ArrayList<Integer> needs = mp.get(i);
        for (int j=0;j<needs.size();j++){
            Integer index = needs.get(j);
            if (courses[index]==0){
                if (visit[index]==1){
                    return false;
                }else {
                    if (!digui(courses,visit,mp,index)){
                       return false;
                    }
                }
            }
        }
        courses[i]=1;
        return true;
    }
}
