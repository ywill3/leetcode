package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Q113 {

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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root==null){
            return res;
        }
        digui(root,targetSum,res,new ArrayList<>());
        return res;
    }

    private void digui(TreeNode root, int targetSum, List<List<Integer>> res, ArrayList<Integer> cur) {
        if(root==null){
            return;
        }
        cur.add(root.val);
        if (root.left==null && root.right==null && root.val==targetSum){
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size()-1);
            return;
        }
        digui(root.left,targetSum-root.val,res,cur);
        digui(root.right,targetSum-root.val,res,cur);
        cur.remove(cur.size()-1);

    }

    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault().getID());
    }
}
