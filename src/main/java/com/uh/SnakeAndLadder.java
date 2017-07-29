package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {

    public static void main(String[] args) {

        int N = 30;
        int[] moves = new int[N];
        for (int i = 0; i < moves.length; i++) {
            moves[i] = -1;
        }

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println(getMinMoves(moves, N));
    }

    private static int getMinMoves(int[] moves, int n) {

        boolean[] vis = new boolean[n];

        Queue<Entry> q = new LinkedList<>();
        q.add(new Entry(0, 0));

        Entry qe = null;
        while (!q.isEmpty()) {

            qe = q.poll();

            int v = qe.v;
            if (v == n-1)
                break;

            for (int i = v + 1; i <= v + 6 && i < n; i++) {

                if (!vis[i]) {

                    int d = qe.d + 1;
                    int val;
                    vis[i] = true;

                    if (moves[i] != -1)
                        val = moves[i];
                    else
                        val = i;
                    q.add(new Entry(val, d));
                }

            }

        }

        if (qe != null)
            return qe.d;

        return 0;
    }

}

class Entry {
    int v;
    int d;

    public Entry(int v, int d) {
        this.v = v;
        this.d = d;
    }
}
