package com.zhulinfeng.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {

    public static String subStr(String originStr) {
        if (null == originStr) return null;

        int beginIndex = 0;
        int endIndex = 0;
        int maxLen = endIndex - beginIndex;
        Set<Character> set = new HashSet<>();

        int begin = 0;
        int end = 0;
        char[] array = originStr.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (set.contains(c)) {
                begin++;
                end++;
                set.remove(c);
            } else {
                end++;
                if (end - begin > maxLen) {
                    beginIndex = begin;
                    endIndex = end;
                    maxLen = endIndex - beginIndex;
                }
            }
            set.add(c);
        }

        return originStr.substring(beginIndex, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(subStr("01234567890123456789abcdefghh"));
    }
}
