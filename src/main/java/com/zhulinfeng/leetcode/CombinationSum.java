package com.zhulinfeng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CombinationSum {

    public void calc(int[] candidates, ArrayList<List<Integer>> list, Stack<Integer> stack, int target, int index) {
        if (0 == target) {
            ArrayList<Integer> ll = new ArrayList<>();
            for (Integer i : stack) ll.add(i);
            list.add(ll);
        }

        for (int i = index; i >= 0; i--) {
            if (target - candidates[i] >= 0) {
                stack.push(candidates[i]);
                calc(candidates, list, stack, target - candidates[i], i-1);
                stack.pop();
            }
            while (i-1 >= 0 && candidates[i] == candidates[i-1]) i--;
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        assert target >= 0;
        Stack<Integer> stack = new Stack<>();
        ArrayList<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);

        calc(candidates, list, stack, target, candidates.length - 1);
        return list;
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> list = new CombinationSum().combinationSum2(candidates, 8);
        for (List ll : list) {
            System.out.println(ll);
        }
    }
}
