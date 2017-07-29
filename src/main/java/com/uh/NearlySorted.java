package com.uh;

import java.util.PriorityQueue;
import java.util.Queue;

public class NearlySorted {

    public static void main(String[] args) {
       
        int k = 3;
        int arr[] = {2, 6, 3, 12, 56, 8};
        
        Queue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i <= k; i++) {
            q.add(arr[i]);
        }
        
        
        while(!q.isEmpty()){
            System.out.println(q.poll());
            if(k<arr.length - 1)
            q.add(arr[++k]);
        }

    }

}
