package com.zhulinfeng.leetcode;

import java.util.Arrays;
import java.util.Scanner;

public class Hotel {
    private int[] prices;
    private int[] daycount;
    private int mindays = Integer.MAX_VALUE;

    public Hotel(int[] prices) {
        this.prices = prices;
        this.daycount = new int[prices.length];
    }

    public int countDays() {
        int count= 0 ;
        for (Integer i : this.daycount) count += i;
        if (0 == count) count = -1;
        return count;
    }

    public void ndays(int index, int money) {
        if (0 == money) {
            if (countDays() < this.mindays) this.mindays = countDays();
            return;
        }
        for (int i = index; i >= 0; i--) {
            if (money - this.prices[i] >= 0) {
                this.daycount[i]++;
                ndays(i, money - this.prices[i]);
                this.daycount[i]--;
            }
        }
    }

    public void printDay() {
        if (this.mindays != Integer.MAX_VALUE) System.out.println(this.mindays);
        else {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = scanner.nextLine().split(" ");
        int[] prices = new int[list.length - 1];
        int money = 0;

        for (int i = 0; i < list.length - 1; i++) {
            prices[i] = Integer.parseInt(list[i]);
        }
        money = Integer.parseInt(list[list.length-1]);
        Arrays.sort(prices);

        Hotel hotel = new Hotel(prices);
        hotel.ndays(prices.length - 1, money);
        hotel.printDay();
    }
}
