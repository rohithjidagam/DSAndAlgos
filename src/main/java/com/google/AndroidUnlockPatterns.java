package com.google;

public class AndroidUnlockPatterns {

    public static void main(String[] args) {

        AndroidUnlockPatterns a = new AndroidUnlockPatterns();
        int no = a.numberOfPatterns(1, 1);
        System.out.println(no);
    }

    public int numberOfPatterns(int m, int n) {

        int res = 0;
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[9][3] = skip[3][9] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;

        boolean[] vis = new boolean[10];

        for (int i = m; i <= n; i++) {
            res += dfs(skip, vis, 1, i - 1) * 4;
            res += dfs(skip, vis, 2, i - 1) * 4;
            res += dfs(skip, vis, 5, i - 1);
        }

        return res;
    }

    private int dfs(int[][] skip, boolean[] vis, int cur, int remain) {

        if (remain < 0)
            return 0;
        if (remain == 0)
            return 1;

        vis[cur] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            if (!vis[i] && (skip[cur][i] == 0 || vis[skip[cur][i]])) {
                res += dfs(skip, vis, i, remain - 1);
            }
        }
        vis[cur] = false;
        return res;
    }

}
