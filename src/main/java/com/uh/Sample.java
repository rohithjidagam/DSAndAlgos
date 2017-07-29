package com.uh;

import java.util.ArrayList;
import java.util.List;

class Node2{
    int data;
}
public class Sample {

    public static void main(String[] args) {

        interleavingStrings();
        
      
        int[] a = {1, 5, 3, 19, 18, 25};
        
        int minE = a[0], minV = Integer.MAX_VALUE;
        
        for (int i = 1; i < a.length; i++) {
            
            if(a[i] - minE < minV){
                minV = a[i] - minE;
            }
            
            if(a[i] < minE){
                minE = a[i];
            }
        }
        
        System.out.println(minV);
        
        
        
        
        
        
    }

    private static void interleavingStrings() {
        String str1 = "AB";
        String str2 = "CD";
        String str3 = "ABCDE";

        int i = 0, j = 0, k = 0;
        while (k != str3.length()) {
            if(i< str1.length() && str1.charAt(i) == str3.charAt(k)){
                i++;
            }
            else if(j< str2.length() &&str2.charAt(j) == str3.charAt(k)){
                j++;
            } else{
                System.out.println("False");
            }
            k++;
        }
    }

}
