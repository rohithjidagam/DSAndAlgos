package com.google;

public class UglyNumbers {

    public static void main(String[] args) {

        boolean isUgly = isUgly(5832);
        System.out.println(isUgly);
        System.out.println(isUgly(1234));
        System.out.println(isUgly(-15));
        System.out.println(isUgly(15000));
        System.out.println(nthUglyNumber(150));
    }

    private static int nthUglyNumber(int n) {

        int[] ugly = new int[n];
        ugly[0] = 1;

        int m2 = 2;
        int m3 = 3;
        int m5 = 5;

        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int next = 1;
        for (int i = 1; i < n; i++) {
            next = Math.min(m2, Math.min(m3, m5));
            ugly[i] = next;
            if (next == m2) {
                i1++;
                m2 = 2 * ugly[i1];
            }
            if (next == m3) {
                i2++;
                m3 = 3 * ugly[i2];
            }
            if (next == m5) {
                i3++;
                m5 = 5 * ugly[i3];
            }
        }

        return next;
    }

    private static boolean isUgly(int num) {

        if (num == 0)
            return false;
        if (num == 1)
            return true;
        while (num % 2 == 0)
            num = num / 2;
        while (num % 3 == 0)
            num = num / 3;
        while (num % 5 == 0)
            num = num / 5;

        return num == 1;
    }

}
