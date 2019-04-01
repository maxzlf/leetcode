package com.zhulinfeng.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Parentheses {
    private void parentheses(Stack<Character> stack, ArrayList<String> list, int rleft, int rright) {
        if (0 == rleft) {
            for (int i = 0; i < rright; i++) stack.push(')');
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < stack.size(); i++) str.append(stack.get(i));
            list.add(str.toString());
            for (int i = 0; i < rright; i++) stack.pop();
            return;
        }

        if (rleft < rright) {
            stack.push(')');
            parentheses(stack, list, rleft, rright-1);
            stack.pop();
        }
        stack.push('(');
        parentheses(stack, list, rleft-1, rright);
        stack.pop();
    }

    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        parentheses(stack, list, n, n);
        return list;
    }

    public int longestValidParentheses(String s) {
        Stack<Character> parenthese = new Stack<>();
        Stack<Integer> index = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c) {
                parenthese.push(c);
                index.push(i);
            } else {
                if (!parenthese.isEmpty() && '(' == parenthese.peek()) {
                    parenthese.pop();
                    index.pop();
                } else {
                    parenthese.push(c);
                    index.push(i);
                }
            }
        }

        int cursor = -1, maxlength = 0;
        for (Integer i : index) {
            maxlength = i - cursor - 1 > maxlength? i - cursor - 1 : maxlength;
            cursor = i;
        }
        maxlength = s.length() - 1 - cursor > maxlength? s.length() - 1 - cursor : maxlength;
        return maxlength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //List<String> list = new Parentheses().generateParenthesis(scanner.nextInt());
        //for (String s : list) System.out.println(s);
        Parentheses parentheses = new Parentheses();
        System.out.println(parentheses.longestValidParentheses(scanner.nextLine()));
    }
}
