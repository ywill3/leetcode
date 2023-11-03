package com.will.leetcode;

public class Q88 {
    public static void main(String[] args) {
        int num1[] = new int[]{0};
        int m = 0;
        int num2[] = new int[]{-1};
        int n = 1;
        merge(num1,m,num2,n);
        for(int i=0;i<m+n;i++){
            System.out.println(num1[i]);
        }

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        if (m==0){
            for(int k=0;k<n;k++){
                nums1[j++]=nums2[k];
            }
            return;
        }


        for (int i=0;i<n;i++){
            if(j-i==m){
                for(int k=i;k<n;k++){
                    nums1[j++]=nums2[k];
                }
                return;
            }
            if(nums1[j]>nums2[i]){
                // 移位
                for(int k=nums1.length-2;k>=j;k--){
                    nums1[k+1]=nums1[k];
                }
                // 插入
                nums1[j++]=nums2[i];
            }else {
                i--;
                j++;
            }
        }
    }
}
