package com.google;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class MaxStack {

    Stack<Integer> stack;
    Map<Integer, Stack<Integer>> map;
    PriorityQueue<Integer> pq;
    int max;

    public static void main(String[] args) {

        MaxStack m = new MaxStack();
        m.push(5);
        m.push(1);
        m.push(-5);
        System.out.println(m.popMax());
        System.out.println(m.popMax());
        System.out.println(m.top());
        /*
         * m.push(1); m.push(5); System.out.println(m.top());
         * System.out.println(m.popMax()); System.out.println(m.top());
         * System.out.println(m.peekMax()); System.out.println(m.pop());
         * System.out.println(m.top());
         */

    }

    public MaxStack() {
        stack = new Stack<>();
        map = new HashMap<>();
        pq = new PriorityQueue(Collections.reverseOrder());
        max = Integer.MIN_VALUE;
    }

    public void push(int x) {
        int i = stack.size();
        stack.push(x);

        if (x > max) {
            max = x;
        }

        if (!pq.contains(x))
            pq.add(x);

        Stack<Integer> tempStack = map.get(x);
        if (tempStack == null) {
            tempStack = new Stack<>();
        }
        tempStack.push(i);
        map.put(x, tempStack);

    }

    public int pop() {
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {

        Stack<Integer> temp = map.get(max);

        if (temp.size() == 1) {
            pq.poll();
            if (!pq.isEmpty()) {
                max = pq.peek();
            } else
                max = Integer.MIN_VALUE;
        }

        int i = temp.pop();
        int val = stack.get(i);
        stack.remove(i);

        return val;
    }

}
