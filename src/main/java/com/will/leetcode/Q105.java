package com.will.leetcode;

public class Q105 {

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

    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return digui(0,0,inOrder.length-1,preOrder,inOrder);
    }

    private static TreeNode digui(int preStart, int inStart, int inEnd, int[] preOrder, int[] inOrder) {
        if (preStart>preOrder.length-1 || inStart>inEnd){
            return null;
        }
        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int index=-1;
        for(int i=inStart;i<=inEnd;i++){
            if(inOrder[i]==rootVal){
                index=i;
                break;
            }
        }
        root.left=digui(preStart+1,inStart,index-1,preOrder,inOrder);
        root.right=digui(preStart+index-inStart+1,index+1,inEnd,preOrder,inOrder);
        return root;

    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{1,2,4,5,3,6,7};
        int[] inOrder = new int[]{4,2,5,1,6,3,7};
        TreeNode treeNode = buildTree(preOrder, inOrder);
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
