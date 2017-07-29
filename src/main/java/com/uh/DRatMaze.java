package com.uh;

public class DRatMaze {

    static int solution[][];

    public static void main(String[] args) {

        int mat[][] = { { 0, 0, 1, 0 }, { 1, 0, 1, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };

        int N = 4;
        solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }
        System.out.println(findPath(mat, 0, 0, 0, 3, "down"));
    }

    public static int findPath(int[][] maze, int x, int y, int M, int N, String direction) {
        // check if maze[x][y] is feasible to move
        if (x == M - 1 && y == N - 1) {// we have reached
            solution[x][y] = 1;
            return 1;
        }
        if (isSafe(maze, x, y)) {
            // move to maze[x][y]

            solution[x][y] = 1;
            // now rat has four options, either go right OR go down or left or
            // go up
            if (direction != "up" && findPath(maze, x + 1, y, M, N, "down") == 1) { // go
                                                                                    // down
                return 1;
            }
            // else go down
            if (direction != "left" && findPath(maze, x, y + 1, M, N, "right") == 1) { // go
                                                                                       // right
                return 1;
            }
            if (direction != "down" && findPath(maze, x - 1, y, M, N, "up") == 1) { // go
                                                                                    // up
                return 1;
            }
            if (direction != "right" && findPath(maze, x, y - 1, M, N, "left") == 1) { // go
                                                                                       // left
                return 1;
            }
            // if none of the options work out BACKTRACK undo the move
            solution[x][y] = 0;
            return 0;
        }
        return 0;
    }

    static boolean isSafe(int mat[][], int x, int y) {
        return (x >= 0 && x < 4 && y >= 0 && y < 4 && mat[x][y] == 0);
    }

}
