package com.zhulinfeng.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Factors {
    public List factor(long N) {
        assert N > 0;
        ArrayList<Long> list = new ArrayList<>();
        list.add(1L);

        long i = 2;
        while (i <= N) {
            if (N % i == 0) {
                list.add(i);
                N /= i;
            } else i++;
        }

        return list;
    }

    public List factor2(long N) {
        assert N > 0;
        Stack<Long> stack = new Stack<>();
        stack.push(1L);

        long i = 2;
        while (i <= N) {
            if (N % i == 0) {
                if (stack.isEmpty() || stack.peek() != i) stack.push(i);
                N /= i;
            } else i++;
        }

        return stack.subList(0, stack.size());
    }

    private void _factorCombinations(long N, long index, List factors, long fcounts[]) {
        assert N > 0 && factors != null && fcounts !=null;
        if (N < 2) {
            StringBuilder str = new StringBuilder();
            for (int i = factors.size() - 1; i >= 0; i--) {
                str.append(fcounts[i]).append("*").append(factors.get(i)).append(" ");
            }
            System.out.println(str.toString());
            return;
        }

        for (int i = (int)index; i >= 0; i--) {
            if (N % (long)factors.get(i) == 0) {
                fcounts[i]++;
                _factorCombinations(N / (long)factors.get(i), i, factors, fcounts);
                fcounts[i]--;
            }
        }
    }

    public void factorCombinations(long N) {
        assert N > 0;
        List factors = factor2(N);
        factors.remove(0);
        long fcounts[] = new long[factors.size()];

        _factorCombinations(N, factors.size() -1, factors, fcounts);
    }

    public static void main(String[] args) {
        long N = new Scanner(System.in).nextLong();
        Factors factors = new Factors();
        System.out.println(factors.factor(N));
        System.out.println(factors.factor2(N));
        factors.factorCombinations(N);
    }
}
