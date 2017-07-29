package com.uh;

public class NumberOfIslands {

    static int M = 5;
    static int N = 5;

    public static void main(String[] args) {

        int M[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };

         for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        int n = noOfIslands(M);
        System.out.println(n);
    }

    private static int noOfIslands(int[][] m) {

        boolean[][] visited = new boolean[M][N];

        int count = 0;
        
        Sum sum = new Sum();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                if (m[i][j] == 1 && !visited[i][j]) {

                    sum.count = 1;
                    DFS(m, i, j, visited,sum);
                    count++;
                    if(max < sum.count)
                        max = sum.count;
                }

            }
        }
        
        System.out.println(max);

        return count;

    }

    private static void DFS(int[][] m, int i, int j, boolean[][] visited, Sum sum) {


        int[][] rc = { { -1, -1 }, { -1, 0 }, { -1, 1 },{ 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        visited[i][j] = true;

      //  for (int k = i - 1; k <= i + 1 && k < M; k++) {
        //    for (int l = j - 1; l <= j + 1 && l < N; l++) {

        for (int n = 0; n < rc.length; n++) {
            int k = i + rc[n][0];
            int l = j + rc[n][1];
            
                if (isSafe(k, l, m, visited)) {
                    sum.count++;
                    DFS(m, k, l, visited,sum);
                }

           // }
        }
    }

    private static boolean isSafe(int k, int l, int[][] m2, boolean[][] visited) {
        return k >= 0 && l >= 0 && k < M && l < N && m2[k][l] == 1 && !visited[k][l];
    }

}
