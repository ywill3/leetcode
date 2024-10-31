package com.will.leetcode.P150To200;


public class Q167 {
    public int[] twoSum(int[] numbers, int target) {
        for (int i=0;i<numbers.length;i++){
            int cha = target - numbers[i];
            int res = binarySearch(numbers,i+1,numbers.length-1,cha);
            if (res!=-1){
                return new int[]{i+1,res+1};
            }
        }
        return new int[]{1,1};
    }

    private int binarySearch(int[] numbers,int start,int end,int tar){
        while (start<=end){
            int mid = (start+end)/2;
            if (numbers[mid]==tar){
                return mid;
            }else if (numbers[mid]>tar){
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q167 q = new Q167();
        int[] ints = q.twoSum(new int[]{2,3,4}, 6);
        System.out.println(ints[0]+":"+ints[1]);

    }
}
