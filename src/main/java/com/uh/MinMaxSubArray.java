package com.uh;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinMaxSubArray {

    public static void main(String[] args) {

        int k = 3;
        int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };

        usingMaxeap(k, arr);

        usingDQueue(k, arr);

        int[] a = { 2, 5, -1, 7, -3, -1, -2 };
        sumOfMinMax(a, 4);

    }

    private static void sumOfMinMax(int[] arr, int k) {

        Deque<Integer> s = new LinkedList<>();
        Deque<Integer> g = new LinkedList<>();
        int i;
        for (i = 0; i < k; i++) {

            while (!s.isEmpty() && arr[i] >= arr[s.peekLast()])
                s.pollLast();

            while (!g.isEmpty() && arr[i] <= arr[g.peekLast()])
                g.pollLast();

            s.addLast(i);
            g.addLast(i);

        }
        int sum = 0;
        for (; i < arr.length; i++) {

            sum += arr[s.peekFirst()] + arr[g.peekFirst()];

            while (!s.isEmpty() && s.peekFirst() <= i - k)
                s.pollFirst();

            while (!g.isEmpty() && g.peekFirst() <= i - k)
                g.pollFirst();

            while (!s.isEmpty() && arr[i] >= arr[s.peekLast()])
                s.removeLast();

            while (!g.isEmpty() && arr[i] <= arr[g.peekLast()])
                g.removeLast();

            s.addLast(i);
            g.addLast(i);

        }
        sum += arr[s.peekFirst()] + arr[g.peekFirst()];

        System.out.println(sum);

    }

    private static void usingDQueue(int k, int[] arr) {

        Deque<Integer> dq = new LinkedList<>();

        int i;
        for (i = 0; i < k; i++) {

            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }

        for (; i < arr.length; i++) {

            System.out.println(arr[dq.peekFirst()]);

            while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();

            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);

        }
        System.out.println(arr[dq.peekFirst()]);

    }

    private static void usingMaxeap(int k, int[] arr) {
        Queue<IntegersMax> max = new PriorityQueue<IntegersMax>();

        int i;
        for (i = 0; i < k; i++) {
            max.add(new IntegersMax(arr[i]));
        }
        System.out.println(max.peek());
        for (; i < arr.length; i++) {
            max.remove(i - k);
            max.add(new IntegersMax(arr[i]));
            System.out.println(max.peek());
        }
    }

}
