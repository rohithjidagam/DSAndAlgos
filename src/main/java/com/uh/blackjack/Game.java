package com.uh.blackjack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that contains all the methods of the Black Jack game.
 * @author 
 *
 */
public class Game {

    Player[] players = null;

    int[] deck = null;

    private static final int MULTIPLICATION_FACTOR = 13;

    private static final String HIT = "Hit";
    private static final int ACE_VALUE = 1;

    /**
     * This is the method to be called first. Pass the number of players. For
     * this excerice it is 2.
     * 
     * @param numOfPlayers
     * @throws IOException
     */
    public void init(int numOfPlayers) throws IOException {
        inializePlayers(numOfPlayers);
        initializeDeck();
        dealInitialRound();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in))) {
            for (int i = 0; i < players.length; i++) {
                System.out.println("now playing "+(i+1)+". His current score is "+players[i].getScore());
                play(i, br);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int winner = findWinner();
        if (winner <= players.length) {
            System.out.println("winner is " + (winner+1));
        } else {
            System.out.println("no winner");
        }
    }

    /**
     *  Initializes an array of length equal to numOfPlayers. Initializes each player object in the array.
     * @param numOfPlayers
     */
    private void inializePlayers(int numOfPlayers) {
        players = new Player[numOfPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
    }

    /**
     * The main method that contains the game algorithm.
     * @param i
     * @param br
     * @throws IOException
     */
    private void play(int i, BufferedReader br) throws IOException {
        Player currPlayer = players[i];
        while (!currPlayer.isInactive()) {
            System.out.println("Type hit to play, stay otherwise");
            String choice = br.readLine();
            if (HIT.equalsIgnoreCase(choice)) {
                int cardValue = pickACard();
                currPlayer.setScore(currPlayer.getScore() + cardValue);
                if (cardValue == ACE_VALUE) {
                    currPlayer.setHasAce(true);
                    currPlayer.setAltScore(currPlayer.getScore() + 10);
                    if (currPlayer.getAltScore() > 21) {
                        currPlayer.setHasAce(false);
                    }
                }
                if (currPlayer.isHasAce()) {
                    System.out.println("player " + (i+1) + " score so far is "
                            + currPlayer.getScore() + " and alt score is "
                            + currPlayer.getAltScore());
                } else {
                    System.out.println("player " + (i+1) + " score so far is "
                            + currPlayer.getScore());
                }
                if (playerBusted(currPlayer.getScore())) {
                    System.out.println("player " + (i+1) + " got busted");
                    currPlayer.setInactive(true);
                }
            } else {
                currPlayer.setInactive(true);
            }
        }
    }

    /** Finds the winner out of all players by comparing their scores. 
     * 
     * @return
     */
    private int findWinner() {
        int maxScoredPlayer = players.length + 1;
        int currMaxScore = 0;
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (!playerBusted(player.getScore())) {
                if (player.getScore() > currMaxScore) {
                    maxScoredPlayer = i;
                    currMaxScore = players[i].getScore();
                }
            }
        }
        return maxScoredPlayer;
    }

    /**
     * Checks if current player`s score is greater than 21.
     * @param score
     * @return
     */
    private boolean playerBusted(int score) {
        return score > 21;
    }

    /**
     * Deals two cards per player.
     */
    private void dealInitialRound() {
        int j = 0;
        while (j < 2) {
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                player.setScore(player.getScore() + pickACard());
                System.out.println("Player "+(i+1)+" score "+player.getScore());
            }
            j++;
        }
    }

    /**
     * Picks a card randomly without repetition.
     * 
     * @return
     */
    private int pickACard() {
        int index = (int) (Math.random() * MULTIPLICATION_FACTOR);
        while (deck[index] <= 0) {
            index = (int) (Math.random() * MULTIPLICATION_FACTOR);
        }
        deck[index]--;
        if (index > 9) {
            index = 9;
        }
        return index + 1;
    }

    /**
     * Initializes an integer array of length 13. Each array location is initialized to 4.This number represents 4 sets of each value.
     */
    private void initializeDeck() {
        deck = new int[13];
        if (deck != null) {
            for (int i = 0; i < deck.length; i++) {
                deck[i] = 4;
            }
        }
    }
}
