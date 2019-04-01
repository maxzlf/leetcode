package com.zhulinfeng.leetcode;

public class ZigZag {
    public String convert(String s, int numRows) {
        assert null != s;
        assert numRows > 0;

        StringBuilder str = new StringBuilder();
        int step = 1 == numRows? 1 : (numRows-1)*2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; ; j++) {
                int index = step * j + i;
                if (index < s.length()) str.append(s.charAt(index));
                else break;
                if (step - i < step && step - i >= numRows) {
                    index = step * j + step - i;
                    if (index < s.length()) str.append(s.charAt(index));
                    else break;
                }
            }
        }
        return str.toString();
    }


    public static void main(String[] args) {
        System.out.println(new ZigZag().convert("PAYPALISHIRING", 3));
    }
}

/*


P A H N
A P L S I I G
Y I R

* */