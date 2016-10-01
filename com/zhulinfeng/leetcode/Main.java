package com.zhulinfeng.leetcode;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            System.out.print(random.nextInt() % 10 + 10 + "  ");
        }
    }

    public static void test() {
        for (int i=0; i<48; i++) {
            for (int j=0; j<120; j++) {
                if (i>=16 && i<=32 && j>=40 && j<=80) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }
}
