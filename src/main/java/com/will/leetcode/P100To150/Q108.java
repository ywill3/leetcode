package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q108 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
//        int len = nums.length;
//        int mid = len/2;
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = digui(0,mid-1,nums);
//        root.right = digui(mid+1,len-1,nums);
        return digui(0,nums.length-1,nums);
    }

    private static TreeNode digui(int left, int right, int[] nums) {
        if (right<left){
            return null;
        }
        int len = right-left+1;
        int mid = left+len/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = digui(left,mid-1,nums);
        root.right = digui(mid+1,right,nums);
        return root;
    }

    public static void main(String[] args) {
/*        int[] nums = new int[]{1,2,3,4,5};
//        System.out.println(1/2);
        TreeNode treeNode = sortedArrayToBST(nums);
        bianli(treeNode);*/
    }


    private static void bianli(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        bianli(root.left);
        bianli(root.right);
    }

}
