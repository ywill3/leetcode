package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q95 {

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

    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (n==0) return res;
        return digui(1,n);
    }

    private static List<TreeNode> digui(int left, int right) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(left>right){
            res.add(null);
            return res;
        }
        if (left==right){
            TreeNode root = new TreeNode(left);
            res.add(root);
            return res;
        }

        for(int i=left;i<=right;i++){
            List<TreeNode> lefts = digui(left, i - 1);
            List<TreeNode> rights = digui(i+1, right);
            for (TreeNode lef:lefts){
                for(TreeNode rig:rights){
                    TreeNode root = new TreeNode(i);
                    root.left = lef;
                    root.right = rig;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        digui(root,res);
        return res;
    }

    private static void digui(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        digui(root.left,res);
        res.add(root.val);
        digui(root.right,res);
    }

    public static void main(String[] args) {
        for (int i=1;i<=10;i++){
            List<TreeNode> treeNodes = generateTrees(i);
            System.out.println(treeNodes.size());
        }

/*        for(TreeNode node:treeNodes) {
            List<Integer> integers = inorderTraversal(node);
            for (int i=0;i<integers.size();i++){
                System.out.print(integers.get(i)+" ");
            }
            System.out.println();
        }*/
    }
}
