package com.zhulinfeng.leetcode;

import java.util.HashMap;

public class RandomizedCollection {
    private HashMap<Integer, Integer> map = new HashMap<>();
    private int totalCount = 0;

    /** Initialize your data structure here. */
    public RandomizedCollection() {

    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (this.map.containsKey(val)) {
            Integer count = this.map.get(val);
            this.map.put(val, ++count);
            this.totalCount++;
            return false;
        } else {
            this.map.put(val, 1);
            this.totalCount++;
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (this.map.containsKey(val)) {
            Integer count = this.map.get(val);
            if (--count <= 0) {
                this.map.remove(val);
            } else {
                this.map.put(val, count);
            }
            this.totalCount--;
            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return 0;
    }
}
