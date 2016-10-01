package com.zhulinfeng.leetcode;


import java.util.Stack;

public class BinaryTree {

    private boolean findPath(TreeNode root, TreeNode node, Stack<TreeNode> path) {
        if (null == node || null == root) return false;
        if (node == root) {
            path.push(root);
            return true;
        } else {
            if (findPath(root.left, node, path)) {path.push(root); return true;}
            if (findPath(root.right, node, path)) {path.push(root); return true;}
            return false;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> ppath = new Stack<>();
        Stack<TreeNode> qpath = new Stack<>();

        findPath(root, p, ppath);
        findPath(root, q, qpath);

        System.out.println(ppath);
        System.out.println(qpath);

        TreeNode commonRoot = null;
        while (!ppath.isEmpty() && !qpath.isEmpty()) {
            if (ppath.peek() == qpath.peek()) {commonRoot = ppath.pop(); qpath.pop();}
            else break;
        }
        return commonRoot;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(new BinaryTree().lowestCommonAncestor(root, root.left, root.right).val);
    }
}
