package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.List;

public class Q173 {
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
    private List<Integer> tp = new ArrayList<>();
    private int index = 0;
    public Q173(TreeNode root) {
        dfs(root);
    }

    private void dfs(TreeNode root){
        if (root==null){
            return;
        }
        dfs(root.left);
        tp.add(root.val);
        dfs(root.right);
    }

    public int next() {
        return tp.get(index++);
    }

    public boolean hasNext() {
        return index<tp.size();
    }
}
