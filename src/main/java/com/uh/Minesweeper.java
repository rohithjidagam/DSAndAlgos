package com.uh;

public class Minesweeper {

    private static final int SIDE = 9;
    private static final int MINES = 10;
    private static int MOVES_LEFT = 10;

    public static void main(String[] args) {

        boolean gameOver = false;
        char[][] realBoard = new char[SIDE][SIDE];
        char[][] myBoard = new char[SIDE][SIDE];
        MOVES_LEFT = SIDE * SIDE - MINES;
        int[][] mines = new int[10][2];
        int[][] moves = new int[MOVES_LEFT][2];

        initialize(realBoard, myBoard);

        placeMines(realBoard, mines);

        assignMoves(moves, MOVES_LEFT);

        printBoard(realBoard);

        int curIndex = 0;
        MCell cell = new MCell();
        while (!gameOver) {

            System.out.println("*********Board**********");
            printBoard(myBoard);

            makeMove(cell, moves, curIndex);

            if (curIndex == 0) {
                if (isMine(cell.x, cell.y, realBoard)) {
                    replaceMine(cell.x, cell.y, realBoard);
                }
            }

            curIndex++;

            gameOver = playMineSweeperUtil(myBoard, realBoard, mines, cell.x, cell.y);

            if (!gameOver && MOVES_LEFT == 0) {
                System.out.println("You won....");
                gameOver = true;
            }

        }
    }

    private static boolean playMineSweeperUtil(char[][] myBoard, char[][] realBoard, int[][] mines, int x, int y) {

        if (myBoard[x][y] != '-')
            return false;

        if (realBoard[x][y] == '*') {
            myBoard[x][y] = '*';

            for (int j2 = 0; j2 < MINES; j2++)
                myBoard[mines[j2][0]][mines[j2][1]] = '*';

            printBoard(myBoard);
            System.out.println("Lost..");
            return true;
        } else {
            int count = countAdjacentMines(x, y, realBoard);
            MOVES_LEFT--;
            myBoard[x][y] = (char)(count + '0');
            if (count != 0) {
                if (isValid(x - 1, y)) {
                    if (!isMine(x - 1, y, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x - 1, y);
                }

                if (isValid(x - 1, y - 1)) {
                    if (!isMine(x - 1, y - 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x - 1, y - 1);
                }

                if (isValid(x, y - 1)) {
                    if (!isMine(x, y - 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x, y - 1);
                }

                if (isValid(x - 1, y + 1)) {
                    if (!isMine(x - 1, y + 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x - 1, y + 1);
                }

                if (isValid(x + 1, y)) {
                    if (!isMine(x + 1, y, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x + 1, y);
                }

                if (isValid(x + 1, y + 1)) {
                    if (!isMine(x + 1, y + 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x + 1, y + 1);
                }

                if (isValid(x + 1, y - 1)) {
                    if (!isMine(x + 1, y - 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x + 1, y - 1);
                }

                if (isValid(x, y + 1)) {
                    if (!isMine(x, y + 1, realBoard))
                        playMineSweeperUtil(myBoard, realBoard, mines, x, y + 1);
                }
            }
        }

        return false;
    }

    private static int countAdjacentMines(int x, int y, char[][] realBoard) {

        int count = 0;

        if (isValid(x - 1, y)) {
            if (isMine(x - 1, y, realBoard))
                count++;
        }

        if (isValid(x - 1, y - 1)) {
            if (isMine(x - 1, y - 1, realBoard))
                count++;
        }

        if (isValid(x, y - 1)) {
            if (isMine(x, y - 1, realBoard))
                count++;
        }

        if (isValid(x - 1, y + 1)) {
            if (isMine(x - 1, y + 1, realBoard))
                count++;
        }

        if (isValid(x + 1, y)) {
            if (isMine(x + 1, y, realBoard))
                count++;
        }

        if (isValid(x + 1, y + 1)) {
            if (isMine(x + 1, y + 1, realBoard))
                count++;
        }

        if (isValid(x + 1, y - 1)) {
            if (isMine(x + 1, y - 1, realBoard))
                count++;
        }

        if (isValid(x, y + 1)) {
            if (isMine(x, y + 1, realBoard))
                count++;
        }

        return count;
    }

    private static boolean isValid(int row, int col) {
        return (row >= 0) && (row < SIDE) && (col >= 0) && (col < SIDE);
    }

    private static void replaceMine(int x, int y, char[][] realBoard) {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (realBoard[i][j] != '*') {
                    realBoard[i][j] = '*';
                    realBoard[x][y] = '-';
                    return;
                }
            }
        }

    }

    private static boolean isMine(int x, int y, char[][] realBoard) {
        if (realBoard[x][y] == '*')
            return true;
        return false;
    }

    private static void assignMoves(int[][] moves, int movesLeft) {

        boolean[] mark = new boolean[SIDE * SIDE];

        for (int i = 0; i < movesLeft; i++) {

            int rand = (int) Math.floor(Math.random() * SIDE * SIDE);
            int x = rand % SIDE;
            int y = rand / SIDE;

            if (!mark[rand]) {
                moves[i][0] = x;
                moves[i][1] = y;

                mark[rand] = true;
            }

        }
    }

    private static void makeMove(MCell cell, int[][] moves, int curIndex) {

        cell.x = moves[curIndex][0];
        cell.y = moves[curIndex][1];

        System.out.println("Current move is:" + cell.x + "," + cell.y);
    }

    private static void printBoard(char[][] realBoard) {

        for (int i = 0; i < realBoard.length; i++) {
            for (int j = 0; j < realBoard.length; j++) {
                System.out.print(realBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void placeMines(char[][] realBoard, int[][] mines) {

        boolean[] mark = new boolean[SIDE * SIDE];

        for (int i = 0; i < MINES; i++) {

            int rand = (int) Math.floor(Math.random() * SIDE * SIDE);
            int x = rand % SIDE;
            int y = rand / SIDE;

            if (!mark[rand]) {
                mines[i][0] = x;
                mines[i][1] = y;

                realBoard[x][y] = '*';
                mark[rand] = true;
            }

        }
    }

    private static void initialize(char[][] realBoard, char[][] myBoard) {

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                realBoard[i][j] = '-';
                myBoard[i][j] = '-';
            }
        }
    }

}

class MCell {
    int x;
    int y;
}
