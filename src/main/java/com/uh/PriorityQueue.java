package com.uh;

import java.util.LinkedList;

public class PriorityQueue {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        java.util.PriorityQueue<Integer> integers = new java.util.PriorityQueue<>();
        
        int[] arr = {-2,0,5,-1,2};
        
        for (int i = 0; i < arr.length; i++) {
            integers.add(arr[i]);
        }
        
        for (int i = 0; i < arr.length; i++) {
           // System.out.println(integers.poll());
        }
    }

}
