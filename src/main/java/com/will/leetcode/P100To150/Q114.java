package com.will.leetcode.P100To150;

import java.util.ArrayList;

public class Q114 {

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
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = pre;
        pre = root;
/*        ArrayList<Integer> tp = new ArrayList<>();
        digui(root,tp);
        for (int i = 1; i < tp.size(); i++) {
            root.left=null;
            root.right=new TreeNode(tp.get(i));
            root=root.right;
        }*/
        /*TreeNode l = root.left;
        TreeNode r = root.right;
        digui1(l,r,root);*/
    }

    private void digui1(TreeNode l, TreeNode r, TreeNode root) {
        if(l==null && r==null){
            return;
        }
        if(l!=null){
            TreeNode n1 = new TreeNode(l.val);
            root.left=null;
            root.right=n1;
            root = root.right;
            digui1(l.left,l.right,root);
        }
        if(r!=null){
            TreeNode n2 = new TreeNode(r.val);
            root.left=null;
            root.right=n2;
            root = root.right;
            digui1(r.left,r.right,root);
        }
    }

    public void digui(TreeNode root,ArrayList<Integer> tp){
        if (root==null){
            return;
        }
        tp.add(root.val);
        digui(root.left,tp);
        digui(root.right,tp);
    }
}
