package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class SumCombination {
    public final int N;
    private int[] ncounts;
    private int count = 0;

    public SumCombination(int N) {
        assert N >= 0;
        this.N = N;
        ncounts = new int[N + 1];
    }

    public void printCount() {
        System.out.println("counts : " + this.count);
    }

    public void printCombination() {
        for (int i = 0; i < this.ncounts.length; i++) {
            if (this.ncounts[i] <= 0) continue;
            System.out.print(this.ncounts[i] + "*" + i + " ");
        }
        System.out.println();
    }

    public void combine(int N, int index) {
        if (0 == N) {
            this.count++;
            printCombination();
            return;
        }

        for (int i = N; i >= index; i--) {
            if (N - i >= 0) {
                this.ncounts[i]++;
                combine(N - i, i);
                this.ncounts[i]--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            SumCombination sumCombination = new SumCombination(scanner.nextInt());
            sumCombination.combine(sumCombination.N, 1);
            sumCombination.printCount();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
