package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class SeqSum {

    private long sum(long n, long m) {
        assert n > 0 && m > 0;
        assert n <= m;

        return (n + m) * (m - n + 1) / 2;
    }

    private void printn2m(long n, long m) {
        assert n > 0 && m > 0;
        StringBuilder str = new StringBuilder();

        while (n <= m) {
            str.append(n).append(" ");
            n++;
        }
        System.out.println(str.toString());
    }

    public void seqSum(long N) {
        assert N >= 0;
        long num = N, lastnum = num;

        while ((num >>= 1) > 0) {
            long left = num;
            long right = num + 1;
            long sum = left + right;
            while (left < right && left < lastnum) {
                if (sum > N) {sum -= left; left++;}
                else if (sum < N) {right++; sum += right;}
                else {printn2m(left, right); break;}
            }
            lastnum = num;
        }
    }

    public void seqSum2(long N) {
        assert N >= 0;

        long sum = 3;
        for (long left = 1, right = 2; left < right && left <= N /2; ) {
            if (sum < N) {sum += ++right;}
            else if (sum > N) {sum -= left++;}
            else {printn2m(left, right); sum -= left++;}
        }
    }

    public void seqSum3(long N) {
        assert N >= 0;

        long middle;
        for (long i = 2; (middle = N/i) >= ((i >> 1) + 1); i++) {
            long radius = i >> 1;
            long n, m;
            if (0x01 == (0x01 & i)) {
                n = middle - radius;
                m = middle + radius;
            } else {
                n = middle - radius + 1;
                m = middle + radius;
            }
            if (N == sum(n, m)) printn2m(n, m);
        }
    }

    public static void main(String[] args) {
        long N = new Scanner(System.in).nextLong();
        SeqSum s = new SeqSum();
        s.seqSum3(N);
        System.out.println();
        s.seqSum2(N);
    }
}
