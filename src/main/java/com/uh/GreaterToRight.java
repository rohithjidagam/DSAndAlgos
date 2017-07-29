package com.uh;

public class GreaterToRight {

    public static void main(String[] args) {

        int arr[] = { 8, 58, 71, 18, 31, 31, 63, 2 };

        int length = arr.length;
        int j = 0, max = arr[length - 1];
        
        for (int i = length - 2; i >= 0; i--) {
            
            int temp  = arr[i];
            arr[i] = max;
            
            if(max < temp){
                max = temp;
            }
            
        }
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
