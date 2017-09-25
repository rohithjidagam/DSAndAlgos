package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {

        int[][] mat = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } };
        
        int m = mat.length;
        int n = mat[0].length;
        
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        
        for (int i = 0; i < atl.length; i++) {
            
        }
        
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(res, mat, i, j);
            }
        }
        
        System.out.println(Arrays.deepToString(res.toArray()));
    }

    private static boolean dfs(List<int[]> res, int[][] mat, int i, int j) {

        int[][] adj = {{-1,0},{1,0},{0,1},{0,-1}};
        
        for (int k = 0; k < adj.length; k++) {
            
            int x = i + adj[k][0];
            int y = j + adj[k][1];
            
            if(x < 0 || y < 0 || x >= mat.length || y >= mat[0].length || mat[i][j] < mat[x][y])
                return false;
            
            if(dfs(res, mat, x, y)){
                res.add(new int[]{x, y});
            }
            
        }
        
        return true;
        
    }

}
