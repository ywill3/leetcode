package com.will.leetcode.P100To150;

import java.time.LocalDate;

public class Q101 {

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

    public boolean isSymmetric(TreeNode root) {

        if (root==null){
            return true;
        }
        return digui(root.left,root.right);
    }

    private boolean digui(TreeNode p, TreeNode q) {
        if(p!=null && q!=null){
            if (p.val==q.val){
                if(digui(p.left,q.right) && digui(p.right,q.left)){
                    return true;
                }
                return false;
            }
        }
        else if(p==null && q!=null){
            return false;
        }
        else if(p!=null && q==null){
            return false;
        }else {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        LocalDate a = LocalDate.of(2023,4,10);

        System.out.println(a.minusDays(31));
    }
}
