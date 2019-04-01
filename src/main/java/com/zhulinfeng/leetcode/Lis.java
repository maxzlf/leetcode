package com.zhulinfeng.leetcode;

import java.util.ArrayList;

public class Lis {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> seq = new ArrayList<>();
        for (int n : nums) {
            if (seq.isEmpty() || seq.get(seq.size() - 1) < n) {
                seq.add(n);
            } else {
                int head = 0, tail = seq.size() - 1, middle;
                while (head < tail) {
                    middle = (head + tail) / 2;
                    if (seq.get(middle) < n) head = middle + 1;
                    else tail = middle - 1;
                }
                seq.set(head, n);
            }
        }
        return seq.size();
    }
}
