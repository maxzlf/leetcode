package com.zhulinfeng.leetcode;

import java.util.Scanner;
import java.util.Stack;

public class LastRemaining {
    public int lastRemaining(int n) {
        assert n > 0;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> current = stack1;
        Stack<Integer> backup = stack2;
        Stack<Integer> tmp = null;

        for (int i = n; i >= 1; i--) current.push(i);

        while (current.size() > 1) {
            while (!current.isEmpty()) {
                current.pop();
                if (!current.isEmpty()) backup.push(current.pop());
            }
            tmp = current;
            current = backup;
            backup = tmp;
        }

        return current.pop();
    }

    public long lastRemaining2(long n) {
        assert n > 0;

        boolean direction = true;
        long step = 1, first = 1, last = n;
        while (first < last) {
            if (direction) {
                if ((last - first) % (step*2) == 0) last -= step;
                first += step;
            } else {
                if ((last - first) % (step*2) == 0) first += step;
                last -= step;
            }
            step <<= 1;
            direction = !direction;
        }
        return first;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(new LastRemaining().lastRemaining2(scanner.nextLong()));
    }
}
