package com.will.leetcode.P100To150;

public class Q129 {

    public static void main(String[] args) {
        Q129 q129 = new Q129();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.right=right;
        root.left=left;
        System.out.println(q129.sumNumbers(root));
    }

    int res = 0;
    public int sumNumbers(TreeNode root) {
        if (root==null){
            return 0;
        }
        digui(root,0);
        return res;
    }

    private void digui(TreeNode node, int pre) {
        if (node == null){
            res += pre;
            return;
        }
        if(node.left==null && node.right==null){
            res+=pre*10+ node.val;
            return;
        }
        if (node.left!=null){
            digui(node.left,pre*10+node.val);
        }
        if (node.right!=null){
            digui(node.right,pre*10+node.val);
        }
    }

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
}
