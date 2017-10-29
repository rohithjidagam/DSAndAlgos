package com.amazon;

import java.util.Arrays;

public class MaxRotate {

    public static void main(String[] args) {

        MaxRotate m = new MaxRotate();
        int max = m.maxRotateFunction(new Integer[] { 4, 3, 2, 6 });
        System.out.println(max);
    }

    public int maxRotateFunction(Integer[] A) {

        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, f(A, i));
        }

        return max;
    }

    int f(Integer[] A, int i) {
        Integer[] B = rotate(A, i, A.length);
        int sum = 0;
        for (int j = 0; j < B.length; j++) {
            sum += j * B[j];
        }
        return sum;
    }

    Integer[] rotate(Integer[] A, int d, int n) {
        Integer[] B = Arrays.copyOf(A, n);
        reverse(B, n - d, n - 1);
        reverse(B, 0, n - d - 1);
        reverse(B, 0, n - 1);
        return B;
    }

    void reverse(Integer[] A, int i, int j) {
        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
    }
}
