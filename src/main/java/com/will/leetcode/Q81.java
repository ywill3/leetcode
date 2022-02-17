package com.will.leetcode;

public class Q81 {

    public static void main(String[] args) {

    }
    public boolean search(int[] nums, int target) {
        return binarySearch(nums,target,0,nums.length-1);
    }

    public boolean binarySearch(int[] nums, int target, int left, int right){
        if (left == right) {
            return nums[left] == target;
        }
        if (nums[left] < nums[right]) {
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        } else {
            int mid = (left + right) / 2;
            if (binarySearch(nums, target, left, mid)) {
                return true;
            }
            return binarySearch(nums, target, mid + 1, right);
        }
    }
}
