package com.zhulinfeng.leetcode;

import java.util.HashMap;

public class IsomorphicString {
    public boolean isIsomorphic(String s, String t) {
        assert null != s && null != t;
        assert s.length() == t.length();
        HashMap<Character, Character> mapst = new HashMap<>();
        HashMap<Character, Character> mapts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (mapst.containsKey(s.charAt(i))) {
                if (mapst.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                mapst.put(s.charAt(i), t.charAt(i));
            }
            if (mapts.containsKey(t.charAt(i))) {
                if (mapts.get(t.charAt(i)) != s.charAt(i)) return false;
            } else {
                mapts.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }
}
