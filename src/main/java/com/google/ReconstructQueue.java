package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReconstructQueue {

    public static void main(String[] args) {

        int[][] arr = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        
        Arrays.sort(arr,  (a,b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]));
        
        List<int[]> res = new ArrayList<int[]>();
        
        for(int[] is : arr){
            res.add(is[1], is);
        }
        
        System.out.println(Arrays.deepToString(res.toArray()));
        
        int[][] out = new int[arr.length][2];
        
        for(int i = 0; i<res.size();i++){
            int[] a = res.get(i);
            out[i][0] = a[0];
            out[i][1] = a[1];
        }
        
        
        
    }

}
