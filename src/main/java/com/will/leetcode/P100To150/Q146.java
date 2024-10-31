package com.will.leetcode.P100To150;

import java.util.*;

public class Q146 {

    public static void main(String[] args) {
        Q146 q = new Q146(10);
        q.put(10,13);
        q.put(3,17);
        q.put(6,11);
        q.put(10,5);
        q.put(9,10);
        System.out.println(q.get(13));
        q.put(2,19);
        System.out.println(q.get(2));
        System.out.println(q.get(3));
        q.put(5,25);
        System.out.println(q.get(8));
        q.put(9,22);
        q.put(5,5);
        q.put(1,30);
        System.out.println(q.get(11));
        q.put(9,12);
        System.out.println(q.get(7));
        System.out.println(q.get(5));
        System.out.println(q.get(8));
        System.out.println(q.get(9));
        q.put(4,30);
        q.put(9,3);
        System.out.println(q.get(9));
        System.out.println(q.get(10));
        System.out.println(q.get(10));
        q.put(6,14);
        q.put(3,1);
        System.out.println(q.get(3));
        q.put(10,11);
        System.out.println(q.get(8));
        q.put(2,14);
        System.out.println(q.get(1));
        System.out.println(q.get(5));
        System.out.println(q.get(4));
        q.put(11,4);
        q.put(12,24);
        q.put(5,18);
        System.out.println(q.get(13));
        q.put(7,23);
        System.out.println(q.get(8));
        System.out.println(q.get(12));
        q.put(3,27);
        q.put(2,12);
        System.out.println(q.get(5));
        q.put(2,9);
        q.put(13,4);
        q.put(8,18);
        q.put(1,7);
        System.out.println(q.get(6));
        q.put(9,29);
        q.put(8,21);
        System.out.println(q.get(5));
        q.put(6,30);
        q.put(1,12);
        System.out.println(q.get(10));
        q.put(4,15);
        q.put(7,22);
        q.put(11,26);
        q.put(8,17);
        q.put(9,29);
        System.out.println(q.get(5));
        q.put(3,4);
        q.put(11,30);
        System.out.println(q.get(12));
        q.put(4,29);
        System.out.println(q.get(3));
        System.out.println(q.get(9));
        System.out.println(q.get(6));
        q.put(3,4);
        System.out.println(q.get(1));
        System.out.println(q.get(10));
        q.put(3,29);
        q.put(10,28);
        q.put(1,20);
        q.put(11,13);
        System.out.println(q.get(3));
        q.put(3,12);
        q.put(3,8);
        q.put(10,9);
        q.put(3,26);
        System.out.println(q.get(8));
        System.out.println(q.get(7));
        System.out.println(q.get(5));
        q.put(13,17);
        q.put(2,27);
        q.put(11,15);
        System.out.println(q.get(12));
        q.put(9,19);
        q.put(2,15);
        q.put(3,16);
        System.out.println(q.get(1));
        q.put(12,17);
        q.put(9,1);
        q.put(6,19);
        System.out.println(q.get(4));
        System.out.println(q.get(5));
        System.out.println(q.get(5));
        q.put(8,1);
        q.put(11,7);
        q.put(5,2);
        q.put(9,28);
        System.out.println(q.get(1));
        q.put(2,2);
        q.put(7,4);
        q.put(4,22);
        q.put(7,24);
        q.put(9,26);
        q.put(13,28);
        q.put(11,26);

    }
    /*Map<Integer,Integer> map;
    Integer capacity;
    long ops = 1;

    long[] tmp = new long[10002];

    public Q146(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        Arrays.fill(tmp, 0);
    }

    public int get(int key) {
        Integer res = map.getOrDefault(key, -1);
        if (res!=-1){
            tmp[key] = System.currentTimeMillis()+ops++;
        }
        return res;
    }

    public void put(int key, int value) {
        tmp[key] = System.currentTimeMillis()+ops++;
        if (map.size() == capacity && !map.containsKey(key)){
           long min = Long.MAX_VALUE;
           int ind = -1;
           for (Integer i:map.keySet()) {
               if (tmp[i]<min && tmp[i]!=0){
                   min = tmp[i];
                   ind = i;
               }
           }
           tmp[ind] = 0;
           map.remove(ind);
        }
        map.put(key,value);
    }*/

    Map<Integer,Integer> map;
    Queue<Integer> queue;
    Integer capacity;
    Map<Integer, Long> tpMap;

    public Q146(int capacity) {
        map = new HashMap<>(capacity);
        queue = new ArrayDeque<>();
        tpMap = new HashMap<>();
        this.capacity = capacity;
    }
    public int get(int key) {
        Integer res = map.getOrDefault(key, -1);
        if (res!=-1){
            queue.add(key);
            tpMap.put(key,tpMap.getOrDefault(key,0L)+1);
        }
        return res;
    }

    public void put(int key, int value) {
        queue.add(key);
        tpMap.put(key,tpMap.getOrDefault(key,0L)+1);
        if (map.size() == capacity && !map.containsKey(key)){
            while (tpMap.get(queue.peek())!=1){
                tpMap.put(queue.peek(),tpMap.get(queue.peek())-1);
                queue.poll();
            }
            Integer pop = queue.poll();
            tpMap.put(pop,tpMap.get(pop)-1);
            map.remove(pop);
        }
        map.put(key,value);
    }
}
