package com.uh;

public class NimGame {

    private static final int COMPUTER = 1;
    private static final int PLAYER = 2;

    // computer starts the game
    private static boolean turn = true;

    public static void main(String[] args) {

        int[] piles = { 3, 4, 5 };
        int n = piles.length;

        // computer starts the game
        knowWinnerBerfore(piles, n);

        playGame(piles, n);

    }

    private static void showPiles(int[] piles, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(piles[i] + " ");
        }
        System.out.println();
    }

    private static boolean gameOver(int[] piles, int n) {
        for (int i = 0; i < n; i++) {
            if (piles[i] != 0)
                return false;
        }
        return true;
    }

    private static void playGame(int[] piles, int n) {

        Pile pile = new Pile();
        while (!gameOver(piles, n)) {
            showPiles(piles, n);
            placeMove(piles, n, pile);

            if (turn) {

            }
        }

    }

    private static void placeMove(int[] piles, int n, Pile pile) {

        int sum = piles[0];
        for (int j = 1; j < n; j++) {
            sum = sum ^ piles[j];
        }

        if (sum != 0) {
            for (int i = 0; i < n; i++) {
                if ((piles[i] ^ sum) < piles[i]) {
                    pile.pI = i;
                    pile.nS = piles[i] - (piles[i] ^ sum);
                    piles[i] = (piles[i] ^ sum);
                    break;
                }
            }
        } else {

        }

    }

    private static void knowWinnerBerfore(int[] piles, int n) {

        int i = piles[0];
        for (int j = 1; j < n; j++) {
            i = i ^ piles[j];
        }

        if (i != 0) {
            System.out.println("Computer Wins..");
        } else {
            System.out.println("Player Wins..");
        }
    }

}

class Pile {
    int pI;
    int nS;
}
