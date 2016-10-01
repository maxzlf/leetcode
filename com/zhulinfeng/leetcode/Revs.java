package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class Revs {

    private void revs(char[] str, int begin, int end) {
        while (begin < end) {
            char tmp = str[begin];
            str[begin] = str[end];
            str[end] = tmp;
            begin++;
            end--;
        }
    }


    public String revs(String stentence) {
        if (null == stentence) return null;
        char[] str = stentence.toCharArray();
        revs(str, 0, str.length - 1);

        int begin = 0;
        for (int end = 0; end < str.length; end++) {
            if (str[end] == ' ' || end + 1 >= str.length) {
                revs(str, begin, end-1);
                begin = end + 1;
            }
        }

        return new String(str);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Revs revs = new Revs();
        System.out.println(revs.revs(scanner.nextLine()));
    }
}
