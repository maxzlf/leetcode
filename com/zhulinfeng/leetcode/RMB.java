package com.zhulinfeng.leetcode;


import java.util.Scanner;

public class RMB {
    private int[] types = new int[] {100, 50, 20, 10, 5, 1};
    private int[] typecount = new int[this.types.length];
    private int count = 0;

    public void printCount() {
        System.out.println("counts : " + this.count);
    }

    public void printCombination() {
        for (int i = 0; i < this.typecount.length; i++) {
            if (this.typecount[i] <= 0) continue;
            System.out.print(this.typecount[i] + "*" + this.types[i] + " ");
        }
        System.out.println();
    }

    public void rmb(int N, int typeindex) {
        if (0 == N) {
            this.count++;
            printCombination();
            return;
        }

        for (int i = typeindex; i < this.types.length; i++) {
            if (N - this.types[i] >= 0) {
                this.typecount[i]++;
                rmb(N - this.types[i], i);
                this.typecount[i]--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a + " " + b);
        try {
            RMB rmb = new RMB();
            rmb.rmb(scanner.nextInt(), 0);
            rmb.printCount();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
