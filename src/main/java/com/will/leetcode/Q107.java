package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q107 {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> res = new Stack<>();
        List<List<Integer>> res1 = new ArrayList<>();
        if (root==null){
            return res;
        }
        digui(res,root,0);
        while (!res.isEmpty()){
            res1.add(res.pop());
        }
        return res1;
    }
    private void digui(Stack<List<Integer>> res, TreeNode root, int index) {
        if (root==null){
            return;
        }
        if (res.size()<=index){
            List<Integer> tp = new ArrayList<>();
            res.push(tp);
        }
        List<Integer> tp1 = res.get(index);
        tp1.add(root.val);
        digui(res,root.left,index+1);
        digui(res,root.right,index+1);
    }
}
