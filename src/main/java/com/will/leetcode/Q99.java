package com.will.leetcode;

public class Q99 {

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

    TreeNode first,second,pre;
    public void recoverTree(TreeNode root) {
        if (root==null)
            return;
        digui(root);
        int tmp = first.val;
        first.val=second.val;
        second.val=tmp;
    }

    private void digui(TreeNode root) {
        if (root==null)
            return;
        digui(root.left);
        if(pre!=null&&root.val<pre.val){
            if (first==null)
                first=pre;
            second=root;
        }
        pre=root;
        digui(root.right);
    }
}
