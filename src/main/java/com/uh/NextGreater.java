package com.uh;

import java.util.Stack;

public class NextGreater {

    public static void main(String[] args) {

        int[] a = { 4, 5, 2, 25 };

        int n = a.length;

        Stack<Integer> s = new Stack<>();

        s.push(a[0]);

        int cur, next;

        for (int i = 1; i < n; i++) {
            next = a[i];

            if (!s.isEmpty()) {
                cur = s.pop();

                while (cur < next) {
                    System.out.println(cur + "-->" + next);
                    if (s.isEmpty())
                        break;
                    cur = s.pop();
                }

                if (cur > next) {
                    s.push(cur);
                }
            }
            s.push(next);
        }

        while (!s.isEmpty()) {
            System.out.println(s.pop() + "-->" + -1);

        }
    }

}
