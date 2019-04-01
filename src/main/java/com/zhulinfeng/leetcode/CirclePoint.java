package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class CirclePoint {
    public int points(int r2) {
        assert r2 > 0;

        int count = 0, r = (int)Math.sqrt(r2);
        for (int x = 1; x <= r; x++) {
            for (int y = r; y >= r - x; y--) {
                if (x*x + y*y == r2) count++;
            }
        }
        return count * 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(new CirclePoint().points(scanner.nextInt()));
    }
}
