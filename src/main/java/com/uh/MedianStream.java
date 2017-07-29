package com.uh;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianStream {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int a[] = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
        
        Queue<IntegersMax> max = new PriorityQueue<IntegersMax>();

        Queue<Integer> min = new PriorityQueue<Integer>();

        int m = Integer.MAX_VALUE;

        System.out.println("{ 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 }");
        System.out.println("Median               Min Heap                   MaxHeap");
        for (int i = 0; i < a.length; i++) {

            int diff = min.size() == max.size() ? 0 : min.size() > max.size() ? -1 : 1;

            switch (diff) {

            case 0://same size
                if (a[i] < m) {
                    max.add(new IntegersMax(a[i]));
                    m = max.peek().i;
                } else {
                    min.add(a[i]);
                    m = min.peek();
                }
                break;
            case 1: //Max has more elements
                if (a[i] < m) {
                    min.add(max.poll().i);
                    max.add(new IntegersMax(a[i]));
                } else {
                    min.add(a[i]);
                }
                m = (max.peek().i + min.peek()) / 2;
                break;
            case -1: //Min has more elements
                if (a[i] < m) {
                    max.add(new IntegersMax(a[i]));
                } else {
                    max.add(new IntegersMax(min.poll()));
                    min.add(a[i]);
                }
                m = (max.peek().i + min.peek()) / 2;
                break;
            }
            System.out.println(m + "                        " + Arrays.deepToString(min.toArray()) + "               "
                    + Arrays.deepToString(max.toArray()));
        }

    }

}

class IntegersMax implements Comparable<IntegersMax> {

    int i;

    public IntegersMax(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(IntegersMax o) {
        return i < o.i ? 1 : -1;
    }

    @Override
    public String toString() {
        return i + "";
    }

}
