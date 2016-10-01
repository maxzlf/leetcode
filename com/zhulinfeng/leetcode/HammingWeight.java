package com.zhulinfeng.leetcode;

public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        final int bit = 0x01;

        for (int i = 0; i < Integer.SIZE * 8; i++) {
            if (bit == (bit & n)) count++;
            n >>>= 1;
        }
        return count;
    }
}
