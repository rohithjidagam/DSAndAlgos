package com.uh;

public class ShortestPathRemote {
    
    /*
     * A B C D E
       F G H I J
       K L M N O
       P Q R S T
       U V W X Y
       Z
       
       Starting 0,0 -> UP,DOWN,LEFT,RIGHT, OK buttons on the remote
       
     */

    public static void main(String[] args) {

       String input  = "COZY";
       
       printDirections(input, input.length());
    }

    private static void printDirections(String s, int n) {
        
        int curX =0; int curY = 0;
        
        for (int i = 0; i < n ; i++) {
            
            int nextX = (s.charAt(i) - 'A')/5;
            int nextY = (s.charAt(i) - 'A')%5;
            
            System.out.println(nextX + "--" + nextY);
            
            while(curX > nextX){
                System.out.println("Move Up");
                curX--;
            }
            
            while(curY > nextY){
                System.out.println("Move Left");
                curY--;
            }
            
            while(curX < nextX){
                System.out.println("Move Down");
                curX++;
            }
            while(curY < nextY){
                System.out.println("Move Right");
                curY++;
            }
            
            System.out.println("Press OK");
            
            
        }

        
    }

}
