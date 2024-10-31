package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q104 {
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

    public int maxDepth(TreeNode root) {
        int res = 0;
        if (root==null){
            return res;
        }
        res = digui(0,root);

        return res;
    }

    private int digui(int i, TreeNode root) {
        if (root==null){
            return i;
        }
        return Math.max(digui(i+1,root.left),digui(i+1,root.right));
    }


}
