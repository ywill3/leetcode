package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q145 {


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

    public static void main(String[] args) {

    }

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        digui(root,res);
        return res;
    }

    private void digui(TreeNode root, List<Integer> res) {
        if (root == null){
            return;
        }

        digui(root.left,res);
        digui(root.right,res);
        res.add(root.val);
    }
}
