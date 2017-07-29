package com.uh.blackjack;
import java.io.IOException;

public class GameRunner {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.init(20);
    }
}
