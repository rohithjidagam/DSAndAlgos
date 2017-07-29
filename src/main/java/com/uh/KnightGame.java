package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class KnightGame {

    public static void main(String[] args) {

        int N = 8;

        CBoard kpos = new CBoard(4, 5, 0);
        CBoard targetPos = new CBoard(1, 1, 0);

        int n = findMinMoves(kpos, targetPos, N);
        System.out.println(n);
        
        boolean flag = findLight(kpos, targetPos, N);
        
        

    }

    private static boolean findLight(CBoard kpos, CBoard targetPos, int n) {


        Queue<CBoard> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];

        q.add(kpos);
        vis[kpos.x][kpos.y] = true;
        
        while(!q.isEmpty()){
            
            CBoard poll = q.poll();
            
            int x = poll.x;
            int y = poll.y;
            int d = poll.dist;
            
            if(x == targetPos.x && y == targetPos.y)
                return true;
            
            checkCell(n, q, vis, x - 1, y - 1, d);
            checkCell(n, q, vis, x - 1, y + 1, d);
            checkCell(n, q, vis, x , y - 1, d);
            checkCell(n, q, vis, x - 1, y, d);
            checkCell(n, q, vis, x + 1, y, d);
            checkCell(n, q, vis, x, y + 1, d);
            checkCell(n, q, vis, x + 1, y - 1, d);
            checkCell(n, q, vis, x + 1, y + 1, d);
            
        }
        return false;
    }

    private static int findMinMoves(CBoard kpos, CBoard targetPos, int n) {

        Queue<CBoard> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];

        q.add(kpos);
        vis[kpos.x][kpos.y] = true;

        while (!q.isEmpty()) {

            CBoard pos = q.poll();
            int x = pos.x;
            int y = pos.y;
            int d = pos.dist;

            if (x == targetPos.x && y == targetPos.y) {
                return d;
            }

            checkCell(n, q, vis, x - 1, y - 2, d);
            checkCell(n, q, vis, x - 1, y + 2, d);
            checkCell(n, q, vis, x + 1, y - 2, d);
            checkCell(n, q, vis, x + 1, y + 2, d);
            checkCell(n, q, vis, x - 2, y - 1, d);
            checkCell(n, q, vis, x - 2, y + 1, d);
            checkCell(n, q, vis, x + 2, y - 1, d);
            checkCell(n, q, vis, x + 2, y + 1, d);

        }

        return 0;
    }

    private static void checkCell(int n, Queue<CBoard> q, boolean[][] vis, int x, int y, int d) {
        if (x >= 0 && x < n && y >= 0 && y < n && !vis[x][y]) {
            vis[x][y] = true;
            q.add(new CBoard(x, y, 1 + d));
        }
    }

}

class CBoard {
    int x, y, dist;

    public CBoard(int i, int j, int d) {
        x = i;
        y = j;
        dist = d;
    }
}
