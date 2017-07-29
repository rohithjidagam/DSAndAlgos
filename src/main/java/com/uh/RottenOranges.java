package com.uh;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    static int R = 3;
    static int C = 5;

    public static void main(String[] args) {

        int arr[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };

        System.out.println(rotOranges(arr));
    }

    private static int rotOranges(int[][] arr) {

        Queue<Cell> q = new LinkedList<Cell>();

        int r = arr.length;
        int c = arr[0].length;
        int count = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == 2) {
                    q.add(new Cell(i, j));
                }
            }
        }

        q.add(new Cell(-1, -1));

        while (!q.isEmpty()) {

            boolean flag = false;

            while (!isDelim(q.peek())) {

                Cell cell = q.poll();

                int x = cell.x;
                int y = cell.y;

                if (isValid(x + 1, y) && arr[x + 1][y] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }
                    arr[x + 1][y] = 2;
                    q.add(new Cell(x + 1, y));
                }

                if (isValid(x - 1, y) && arr[x - 1][y] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }
                    arr[x - 1][y] = 2;
                    q.add(new Cell(x - 1, y));
                }

                if (isValid(x, y + 1) && arr[x][y + 1] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }
                    arr[x][y + 1] = 2;
                    q.add(new Cell(x, y + 1));
                }

                if (isValid(x, y - 1) && arr[x][y - 1] == 1) {
                    if (!flag) {
                        count++;
                        flag = true;
                    }
                    arr[x][y - 1] = 2;
                    q.add(new Cell(x, y - 1));
                }

            }

            q.poll();

            if (!q.isEmpty()) {
                q.add(new Cell(-1, -1));
            }

        }

        return checkAll(arr) ? -1 : count;
    }

    private static boolean checkAll(int[][] arr) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDelim(Cell cell) {
        return cell.x == -1 && cell.y == -1 ? true : false;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C ? true : false;
    }

}

class Cell {
    public Cell(int i, int j) {
        x = i;
        y = j;
    }

    int x;
    int y;
}
