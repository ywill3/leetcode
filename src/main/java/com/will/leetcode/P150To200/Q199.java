package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.List;

public class Q199 {

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

        Q199 q199 = new Q199();
        List<Integer> integers = q199.rightSideView(new Q199().new TreeNode(1, new Q199().new TreeNode(2, new Q199().new TreeNode(5), new Q199().new TreeNode(3)), new Q199().new TreeNode(4)));
        System.out.println(integers);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<TreeNode> tp = new ArrayList<>();
        tp.add(root);
        while (!tp.isEmpty()){
            int size = tp.size();
            List<TreeNode> newTp = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode treeNode = tp.get(i);
                if (i==size-1){
                    res.add(treeNode.val);
                }
                if (treeNode.left!=null){
                    newTp.add(treeNode.left);
                }
                if (treeNode.right!=null){
                    newTp.add(treeNode.right);
                }
            }
            tp=newTp;
        }
        return res;
    }

}
