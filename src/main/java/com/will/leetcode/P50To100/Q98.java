package com.will.leetcode.P50To100;

public class Q98 {

     public class TreeNode {
         long val;
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

    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return true;
         return digui(root,null,null);
    }

    private boolean digui(TreeNode root, TreeNode min, TreeNode max) {
        if(root==null)
            return true;

        if(min!=null && root.val<=min.val){
            return false;
        }
        if(max!=null && root.val>=max.val){
            return false;
        }

        return digui(root.left,min,root) && digui(root.right,root,max);
    }

    private boolean digui(TreeNode root) {
        if(root==null)
            return true;
         long rv = root.val;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left==null && right==null)
            return true;
        boolean res = true;
        if (left!=null){
            if(left.val>=rv){
                return false;
            }
            if(digui(left)){
                res=true;
            }else {
                return false;
            }
        }
        if (right!=null){
            if(right.val<=rv){
                return false;
            }
            if(digui(right)){
                res=true;
            }else {
                return false;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
