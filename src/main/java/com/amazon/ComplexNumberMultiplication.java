package com.amazon;

import java.util.Arrays;

public class ComplexNumberMultiplication {

    public static void main(String[] args) {

        /**
         * (a1+b1i) * (a2+b2i) = (a1a2 + b1b2 + a1b2 + a2b1)
         */
        String res = complex("1+-1i", "1+-1i");
        System.out.println(res);
        
        
        String res2 = complex("1+0i", "1+0i");
        System.out.println(res2);
    }

    private static String complex(String a, String b) {

        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(B[0]);
        
        int a2 = Integer.parseInt(A[1].replace("i", ""));
        int b2 = Integer.parseInt(B[1].replace("i", ""));
        
        int a1a2 = a1 * b1;
        int b1b2 = a2 * b2;
        
        int p  = a1 * b2 + a2 * b1;
        
        String af = a1a2 + (-1 * b1b2) + "";
        String bf = p + "i";
        
        return af+"+"+bf;
    }

}
