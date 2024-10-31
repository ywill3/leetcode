package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q124 {
    static int max_sum;
    public static int calc(TreeNode root){
        if(root==null) return 0;
        int left=calc(root.left);
        int right=calc(root.right);
        int root_left=root.val+left;
        int root_right=root.val+right;
        int all=root.val+left+right;
        max_sum=Math.max(max_sum,Math.max(Math.max(root_left,root_right),Math.max(all,root.val)));
        return Math.max(Math.max(root.val+left,root.val+right),root.val);
    }
    public int maxPathSum(TreeNode root) {
        max_sum=Integer.MIN_VALUE;
        calc(root);
        return max_sum;
    }


    public static void main(String[] args) {
        Q124 q124 = new Q124();
        TreeNode root = q124.new TreeNode(1);
        TreeNode l1 = q124.new TreeNode(-2);
        TreeNode r1 = q124.new TreeNode(-3);
        TreeNode l2 = q124.new TreeNode(1);
        TreeNode r2 = q124.new TreeNode(3);
        TreeNode l3 = q124.new TreeNode(-2);
        TreeNode l4 = q124.new TreeNode(-1);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l1.right = r2;
        l2.left= l4;
        r1.left = l3;
        System.out.println(q124.maxPathSum(root));
    }

    public class TreeNode {
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
}
