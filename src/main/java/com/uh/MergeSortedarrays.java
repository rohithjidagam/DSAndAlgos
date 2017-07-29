package com.uh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeSortedarrays {

    public static void main(String[] args) {

        mergeSortedArrays();

        smallestRangeFromKLists();
        
        sumBetweenK1AndK2();

    }

    private static void sumBetweenK1AndK2() {

        int arr[] = { 20, 8, 22, 4, 12, 10, 14 } ;
        int  k1 = 3 , k2 = 6 ;
        
        Queue<Integer> q = new PriorityQueue<>();
        
        List<Integer> list= new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        
        for (int i = 0; i < k2; i++) {
         q.add(arr[i]);   
        }
        
        for (int i = k2; i < arr.length; i++) {
            int min = q.peek();
            
            if(arr[i] > min){
                q.poll();
                q.add(arr[i]);
            }
        }
        
        q.forEach( a -> System.out.print(a + " "));
        
        
    }

    private static void smallestRangeFromKLists() {

        int arr[][] = { { 4, 7, 9, 12, 15 }, { 0, 8, 10, 14, 20 }, { 6, 12, 16, 30, 50 } };

        int n = arr.length;
        int k = arr[0].length;
        int range = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        Queue<Array> pq = new PriorityQueue<Array>(k);
        for (int i = 0; i < n; i++) {
            pq.add(new Array(arr[i][0], i, 1));
            if (arr[i][0] > max)
                max = arr[i][0];
        }

        while (!pq.isEmpty()) {

            Array root = pq.poll();
            min = root.val;

            if (range > max - min + 1) {
                range = max - min + 1;
                start = min;
                end = max;
            }

            Array next = null;
            int ind = root.index;
            int nextIndex = root.nextIndex;
            if (nextIndex < k) {
                int ele = arr[ind][nextIndex];
                next = new Array(ele, ind, nextIndex + 1);
                if (ele > max) {
                    max = ele;
                }

            } else
                break;
            pq.add(next);
        }

        System.out.println(start + "-" + end);

    }

    private static void mergeSortedArrays() {
        int arr[][] = { { 2, 6, 12, 34 }, { 1, 9, 20, 1000 }, { 23, 34, 90, 2000 } };

        Queue<Array> pq = new PriorityQueue<Array>(3);

        int n = 4;
        int k = arr.length;
        int[] res = new int[n * k + 1];

        Array a = null;
        for (int i = 0; i < k; i++) {
            a = new Array(arr[i][0], i, 1);
            pq.add(a);
        }

        int i = 0;
        List<Integer> inte = new ArrayList<>();
        while (!pq.isEmpty()) {
            Array ar = pq.poll();
            res[i] = ar.val;
            i = i + 1;
            int in = ar.index;
            int nI = ar.nextIndex;
            inte.add(ar.val);
            // System.out.println(i +"-" +res[i] + "-" +ar.val + "-" + in+"-"
            // +nI);
            if (nI < n) {
                Array next = new Array(arr[in][nI], in, nI + 1);
                pq.add(next);
            }

        }

        for (Integer integer : inte) {
            System.out.println(integer);
        }
    }

}

class Array implements Comparable<Array> {

    int val;
    int index;
    int nextIndex;

    Array(int v, int i, int nI) {
        val = v;
        index = i;
        nextIndex = nI;
    }

    @Override
    public int compareTo(Array o2) {
        return val > o2.val ? 1 : -1;
    }

    @Override
    public String toString() {
        return val + "";
    }

}
