package com.will.leetcode;

public class Q106 {

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

    public static TreeNode buildTree(int[] inOrder, int[] postOrder) {
        return digui(postOrder.length-1,0,inOrder.length-1,postOrder,inOrder);
    }

    private static TreeNode digui(int postEnd, int inStart, int inEnd, int[] postOrder, int[] inOrder) {
        if (postEnd<0 || inStart>inEnd){
            return null;
        }
        int rootVal = postOrder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int index=-1;
        for(int i=inStart;i<=inEnd;i++){
            if(inOrder[i]==rootVal){
                index=i;
                break;
            }
        }
        root.left=digui(postEnd-(inEnd-index)-1,inStart,index-1,postOrder,inOrder);
        root.right=digui(postEnd-1,index+1,inEnd,postOrder,inOrder);
        return root;

    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{1,2,4,5,3,6,7};
        int[] inOrder = new int[]{4,2,5,1,6,3,7};
        int[] postOrder = new int[]{4,5,2,6,7,3,1};
        TreeNode treeNode = buildTree(inOrder,postOrder);
        bianli(treeNode);
    }

    private static void bianli(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        bianli(root.left);
        bianli(root.right);
    }


}
