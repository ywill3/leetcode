package com.will.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q94 {

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
         TreeNode left = null;
         TreeNode right = new TreeNode(2);
         TreeNode root = new TreeNode(1);
         TreeNode rl = new TreeNode(3);
         right.left=rl;
//         root.left=left;
//         root.right=right;
        List<Integer> integers = inorderTraversal(root);
        for (int i=0;i<integers.size();i++){
            System.out.print(integers.get(i)+" ");
        }
    }


}
