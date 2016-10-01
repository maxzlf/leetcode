package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class Candy {
    public void candy(int A_B, int B_C, int AB, int BC) {
        if (0x01 == ((B_C + BC) & 0x01) || 0x01 == ((A_B + AB) & 0x01)) {
            System.out.println("No");
            return;
        }

        int B = (B_C + BC) / 2;
        int A = A_B + B;
        int C = B - B_C;
        if (A + B == AB) {
            System.out.println(A + " " + B + " " + C);
        } else {
            System.out.println("No");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A_B = scanner.nextInt();
        int B_C = scanner.nextInt();
        int AB = scanner.nextInt();
        int BC = scanner.nextInt();

        new Candy().candy(A_B, B_C, AB, BC);
    }
}
