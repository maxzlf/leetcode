package com.zhulinfeng.leetcode;

import java.util.ArrayList;

public class RecoverTree {
    private ArrayList<TreeNode> revslinks = new ArrayList<>();

    private void findRevs(TreeNode node) {
        if (null == node) return;
        if (null != node.left && node.val < node.left.val) {
            this.revslinks.add(node.left);
            this.revslinks.add(node);
        }
        if (null != node.right && node.val > node.right.val) {
            this.revslinks.add(node);
            this.revslinks.add(node.right);
        }
        findRevs(node.left);
        findRevs(node.right);
    }

    private void swapNode(TreeNode node1, TreeNode node2) {
        if (null == node1 || null == node2) return;
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    public void recoverTree(TreeNode root) {
        findRevs(root);
        if (2 == this.revslinks.size()) {
            swapNode(revslinks.get(0), revslinks.get(1));
        } else if (4 == this.revslinks.size()) {
            if (revslinks.get(0).val != revslinks.get(3).val) swapNode(revslinks.get(0), revslinks.get(3));
            else swapNode(revslinks.get(1), revslinks.get(2));
        } else {

        }
    }
}
