package com.uh;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  /*
    0 (0,0) 1(0,1) 2(0,2)
    3(1,0) 4(1,1) 5 (1,2)
    6(2,0) 7(2,1) 8(2,2)
  
  */
  
  
  /*
  
  0 -> 0,0
  1 -> 0,1
  */
  
  static final Character comp = 'X';
  static final Character player = 'O';
  static boolean turn = true;
  static Character ch = '*';
  static Character[][] board = new Character[3][3];
  //player starts the game
  public static void main(String[] args) {
   
    
    
  
    
    Map<Integer, Position> map = new HashMap<>();
    intializeMap(map);
    
    
    
    System.out.println(playGame(map));
    

       
}
  
  static String playGame(Map<Integer, Position> map){
    
    
      while(!isBoardFull(board)){
    Random r = new Random();
    int n = r.nextInt(9);
    
        System.out.println(n);
        System.out.println(turn);
    Position p = map.get(n); 
    
    if(turn){
      System.out.println(board[p.i][p.j] + "--" + board[p.j]);
      if(board[p.i][p.j] == null){
        System.out.println("Here");
        board[p.i][p.j] = player;
        ch = player;
        turn = false;
      }
    } else{
        if(board[p.i][p.j] == null){
          board[p.i][p.j] = comp;
          ch = comp;
          turn = true;
        }
           
    }
        
        printBoard(board);
           
    Character c = checkWinner(board, ch);
           
    if(ch == player) return "Player wins";
    else if(ch == comp) return "Computer wins";          
       
  }
           
     return "Draw";
  
  
  }
  
  static void printBoard(Character[][] board){
  
     for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
           System.out.print(board[i][j] + " ");
           }
       System.out.println();
         }
  }
    

           
           static Character checkWinner(Character[][] board, char ch){
           
            //check rows
             if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][2 ] == ch) return ch;
             if(board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][2 ] == ch) return ch;
             if(board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][2 ] == ch) return ch;
             
             //check cloumns
                          if(board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[2][0] == ch) return ch;
             if(board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[2][1 ] == ch) return ch;
             if(board[0][2] == board[1][2] && board[1][2] == board[1][2] && board[2][2 ] == ch) return ch;
             
             //check diagnoals
             
             if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == ch) return ch;
              if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == ch) return ch;
             
          return null;
             
             
           }
       
       static boolean isBoardFull(Character[][] board){
       
         boolean isFull = true;
         for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
            if(board[i][j] == null){
              isFull = false;
            }
           }
         }
             return isFull;
       }
         
      static void intializeMap(Map<Integer, Position> map){
         
         map.put(0, new Position(0,0));
           map.put(1, new Position(0,1));
           map.put(2, new Position(0,2));
           map.put(3, new Position(1,0));
           map.put(4, new Position(1,1));
           map.put(5, new Position(1,2));
           map.put(6, new Position(2,0));
           map.put(7, new Position(2,1));
           map.put(8, new Position(2,2));
           
         }
}
       
       
           
           
           class Position{
         
         int i, j;
         
         
         public Position(int i, int j){
         
           this.i=i;
           this.j=j;
         }
         
       }


/* 
Your previous Plain Text content is preserved below:

See this link for a demo of 3x3 Tic Tac Toe: https://playtictactoe.org/
See this link for a demo of Ultimate Tic Tac Toe: http://bejofo.net/ttt/

First, Implement 3x3 Tic Tac Toe
Then, Implement Ultimate Tic Tac Toe

Tips:
- Don't worry too much about algorithmic complexity--the board is only of size 9.
- There might not be enough time to completely finish a solution, so we're looking for your ability to decompose, write clean, functional code, and communication of ideas.
- Coderpad has an issue with accepting user input, so we recommend choosing the next move randomly.


 */