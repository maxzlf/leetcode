package com.zhulinfeng.leetcode;

import java.util.Scanner;

public class StringAdd {
    boolean checkString(String str) {
        if (null == str) return true;
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') return false;
        }
        return true;
    }


    String addString(String a, String b) {
        if (!checkString(a) || !checkString(b)) return "error";
        if (null == a) return b;
        if (null == b) return a;

        StringBuilder str = new StringBuilder(Math.max(a.length(), b.length()));
        int carray = 0;
        for (int i = 0, j = 0; i < a.length() || j < b.length(); i++, j++) {
            int x = i < a.length()? a.charAt(i) - '0' : 0;
            int y = j < b.length()? b.charAt(j) - '0' : 0;
            int sum = x + y + carray;
            str.append((char)(sum % 10 + '0'));
            carray = sum / 10;
        }
        if (carray > 0) str.append((char)(carray + '0'));
        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String strs[] = scan.next().split(",");
        if (strs.length != 2) System.out.println("error");
        System.out.println(new StringAdd().addString(strs[0], strs[1]));
    }
}
