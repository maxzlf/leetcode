package com.zhulinfeng.leetcode;

public class UglyNumber {

    public int nthUglyNumber(int n) {
        if( n == 0 || n == 1) {
            return n;
        }

        int[] index = {0, 0, 0};
        int[] factors = {2, 3, 5};
        int[] result = new int[n];
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < index.length; j++) {
                int tmp = result[index[j]] * factors[j];
                if (tmp < min) min = tmp;
            }

            for (int j = 0; j < index.length; j++) {
                if (min == result[index[j]] * factors[j]) index[j]++;
            }

            result[i] = min;
        }
        return result[n-1];
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(new UglyNumber().nthUglyNumber(scanner.nextInt()));
        int a = 5;
        System.out.println();
    }
}


