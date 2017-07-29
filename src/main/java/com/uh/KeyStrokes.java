package com.uh;

public class KeyStrokes {

    static int N = 20;

    public static void main(String[] args) {

        for (int i = 0; i <= N; i++) {
            System.out.println(i + "-->" + printOptimal(i));
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(i + "-->" + printOptimalDP(i));
        }
    }

    private static int printOptimalDP(int n) {

        if (n < 7)
            return n;
        int[] cost = new int[N];

        int i, b;
        for (i = 1; i < 7; i++) {
            cost[i - 1] = i;
        }

        for (i = 7; i <= N; i++) {
            cost[i - 1] = 0;

            for (b = i - 3; b > 0; b--) {

                int cur = (i - b - 1) * cost[b - 1];
                if (cur > cost[i - 1])
                    cost[i - 1] = cur;
            }

        }

        return cost[n - 1];
    }

    private static int printOptimal(int n) {

        if (n < 7)
            return n;

        int max = 0;

        for (int i = n - 3; i > 0; i--) {

            int cur = (n - i - 1) * printOptimal(i);
            if (cur > max)
                max = cur;
        }
        return max;
    }

}
