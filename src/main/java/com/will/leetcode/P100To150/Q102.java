package com.will.leetcode.P100To150;

import java.util.ArrayList;
import java.util.List;

public class Q102 {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root==null){
            return res;
        }
        digui(res,root,0);

        return res;
    }

    private void digui(List<List<Integer>> res, TreeNode root, int index) {
        if (root==null){
            return;
        }
        if (res.size()<=index){
            List<Integer> tp = new ArrayList<>();
            res.add(tp);
        }
        List<Integer> tp1 = res.get(index);
        tp1.add(root.val);
        digui(res,root.left,index+1);
        digui(res,root.right,index+1);
    }
}
