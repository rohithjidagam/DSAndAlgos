package com.uh;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void main(String[] args) {

        Integer[] arr = { 12, 11, 13, 5, 6 };
        int n = arr.length;

        bubbleSort(arr, n);
        System.out.println(Arrays.deepToString(arr));

        optimizedBubbleSort(arr, n);
        System.out.println(Arrays.deepToString(arr));

        insertionSort(arr, n);
        System.out.println(Arrays.deepToString(arr));

        selectionSort(arr, n);
        System.out.println(Arrays.deepToString(arr));
    }

    private static void selectionSort(Integer[] arr, int n) {

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            swap(arr, i, min);
        }
    }

    private static void insertionSort(Integer[] arr, int n) {

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void optimizedBubbleSort(Integer[] arr, int n) {

        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    private static void bubbleSort(Integer[] arr, int n) {

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(Integer[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
