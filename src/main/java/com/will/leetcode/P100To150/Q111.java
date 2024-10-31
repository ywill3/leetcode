package com.will.leetcode.P100To150;

public class Q111 {

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

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);
        if (leftHeight==0){
            return rightHeight+1;
        }else if (rightHeight==0) {
            return leftHeight + 1;
        }else {
            return Math.min(leftHeight,rightHeight)+1;
        }
    }


}
