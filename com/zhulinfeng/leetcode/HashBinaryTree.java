package com.zhulinfeng.leetcode;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HashBinaryTree {
    private class HashNode {
        long key;
        long value;
        HashNode left;
        HashNode right;

        HashNode(long value) {
            this.value = value;
            ByteBuffer byteBuffer = ByteBuffer.allocate(Long.SIZE);
            this.key = MurmurHash.hash64(byteBuffer.putLong(value).array(), Long.SIZE);
            this.left = null;
            this.right = null;
        }
    }

    private HashNode root = null;
    private long size = 0;

    public boolean isEmpty() {
        return null == this.root;
    }

    public long size() {
        return this.size;
    }

    public void add(long num) {
        this.size++;
        HashNode node = new HashNode(num);

        if (isEmpty()) {
            this.root = node;
            return;
        }
        HashNode father = this.root;
        HashNode child = this.root;
        while (null != child) {
            father = child;
            if (node.key > child.key) child = child.right;
            else child = child.left;
        }
        if (node.key > father.key) father.right = node;
        else father.left = node;
    }

    public boolean has(long num) {
        HashNode tmpNode = new HashNode(num);
        HashNode current = this.root;

        while (null != current) {
            if (tmpNode.key > current.key) current = current.right;
            else if (tmpNode.key < current.key) current = current.left;
            else {
                if (tmpNode.value != current.value) current = current.left;
                else break;
            }
        }

        return null != current;
    }

    public List toList() {
        ArrayList<Long> list = new ArrayList<>();
        toList(list, this.root);
        return list;
    }

    private void toList(ArrayList<Long> list, HashNode root) {
        assert null != list;
        if (null == root) return;

        this.toList(list, root.left);
        list.add(root.value);
        this.toList(list, root.right);
    }

    public static void main(String[] args) {
        HashBinaryTree tree = new HashBinaryTree();
        for (long i = 0; i < 10000; i++) {
            tree.add(i);
            assert tree.has(i);
        }
        System.out.println(tree.toList());
        System.out.println("size : " + tree.size());
        System.out.println(tree.has(100));
    }
}
