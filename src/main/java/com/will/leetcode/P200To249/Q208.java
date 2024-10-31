package com.will.leetcode.P200To249;

public class Q208 {

    public class Tree{
        char val;
        Tree[] next;
        boolean[] flag;
        Tree(char val){
            this.val = val;
            next = new Tree[27];
            flag = new boolean[27];
        }
    }
    Tree root;
    public Q208() {
         root = new Tree('1');
    }

    public void insert(String word) {
        Tree tmp = root;
        Tree[] next = new Tree[27];
        boolean[] flag = new boolean[27];
        for (int i=0;i<word.length();i++){
            next = tmp.next;
            flag = tmp.flag;
            char c = word.charAt(i);
            int index = c-'a';
            if (flag[index]){
                tmp = next[index];
            }else {
                Tree tree = new Tree(c);
                next[index] = tree;
                flag[index] = true;
                tmp = next[index];
            }
        }
        next = tmp.next;
        flag = tmp.flag;
        Tree tree = new Tree('1');
        next[26] = tree;
        flag[26] = true;
    }

    public boolean search(String word) {
        Tree tmp = root;
        Tree[] next = new Tree[27];
        boolean[] flag = new boolean[27];
        for (int i=0;i<word.length();i++){
            next = tmp.next;
            flag = tmp.flag;
            char c = word.charAt(i);
            int index = c-'a';
            if (!flag[index]){
                return false;
            }
            tmp = next[index];
        }
        flag = tmp.flag;
        if (flag[26]){
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        Tree tmp = root;
        for (int i=0;i<prefix.length();i++){
            Tree[] next = tmp.next;
            boolean[] flag = tmp.flag;
            char c = prefix.charAt(i);
            int index = c-'a';
            if (!flag[index]){
                return false;
            }
            tmp = next[index];
        }
        return true;
    }

    public static void main(String[] args) {
        Q208 q = new Q208();
        q.insert("apple");
        System.out.println(q.search("apple"));
        System.out.println(q.search("app"));
        System.out.println(q.startsWith("app"));
        q.insert("app");
        System.out.println(q.search("app"));
    }


}
