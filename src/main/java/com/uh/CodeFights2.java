package com.uh;

public class CodeFights2 {

    public static void main(String[] args) {

        CodeFights2 cf = new CodeFights2();

        cf.arrayPacking();
    }

    private void arrayPacking() {

        int[] arr = { 24, 85, 0 };
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += decToBin(arr[i]);
        }

        int res = 0;
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            int n = s.charAt(i) - '0';
            res += Math.pow(2, k++) * n;
        }
        System.out.println(res);
    }

    String decToBin(int n) {
        String s = "";
        while (n != 0) {
            s += n % 2;
            n = n / 2;
        }

        int l = s.length();
        if (l != 8) {
            for (int i = 0; i < 8 - l; i++) {
                s = s + "0";
            }
        }
        return s;

    }

}
