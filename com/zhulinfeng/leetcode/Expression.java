package com.zhulinfeng.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Expression {

    public String expression(String expression) {
        if (null == expression) return null;
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 0);
        priority.put('-', 0);
        priority.put('*', 1);
        priority.put('/', 1);
        priority.put('(', 0);
        priority.put(')', 0);

        StringBuilder str = new StringBuilder(expression.length());
        Stack<Character> stack = new Stack<>();
        for (Character c : expression.toCharArray()) {
            if (null == priority.get(c)) str.append(c);
            else {
                if (stack.isEmpty()) {
                    if (')' == c) return "error";
                    else stack.push(c);
                }
                else {
                    if (')' == c) {
                        char tmp;
                        while (!stack.isEmpty() && '(' != (tmp = stack.pop())) str.append(tmp);
                    }
                    else if ('(' == c || '(' == stack.peek() || priority.get(stack.peek()) < priority.get(c)) stack.push(c);
                    else {
                        while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c)) {
                            str.append(stack.pop());
                        }
                        stack.push(c);
                    }
                }
            }
        }
        while (!stack.isEmpty()) str.append(stack.pop());

        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println(new Expression().expression(scanner.nextLine()));
        LinkedList<Integer> queue = new LinkedList<>();
        int num;
        while ((num = scanner.nextInt()) != 0) {
            queue.push(num);
            queue.push(scanner.nextInt());
            System.out.println(queue.pollLast());
        }
    }
}
