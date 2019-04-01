package com.zhulinfeng.leetcode;


class Node {
    int val;
    Node next;
    Node(int x) { val = x; }
}


public class ListNode {
    public Node addTwoNumbers(Node l1, Node l2) {
        Node head = null, current = null;
        int carray = 0, n1, n2;

        while (null != l1 || null != l2 || 0 != carray) {
            n1 = null == l1? 0 : l1.val;
            n2 = null == l2? 0 : l2.val;
            int sum = n1 + n2 + carray;
            int digit = sum % 10;
            carray = sum / 10;
            Node tmp = new Node(digit);
            if (null == current) {
                current = tmp;
                head = tmp;
            } else {
                current.next = tmp;
                current = tmp;
            }
            l1 = null == l1? null : l1.next;
            l2 = null == l2? null : l2.next;
        }

        return head;
    }
}
