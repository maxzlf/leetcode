package com.zhulinfeng.leetcode;

public class Rectangle {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int hight = 0, width = 0;

        if (A <= G && E <= C) {
            int start = A >= E? A : E;
            int end = C >= G? G : C;
            width = end - start;
        }

        if (B <= H && F <= D) {
            int start = B >= F? B : F;
            int end = D >= H? H : D;
            hight = end - start;
        }

        int common = width * hight;
        return (C-A)*(D-B) + (G-E)*(H-F) - common;
    }

    public static void main(String[] args) {
        System.out.println(new Rectangle().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
