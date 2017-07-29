package com.uh;

import java.util.HashMap;

public class PhonNumberMapping
{
    // A Recursive function to print all combinations
    // that can be used to dial a given number.
    // phNo ==> Given Phone Number
    // i    ==> Current digit of phNo to be processed
    // hm   ==> Stores characters that can be used to
    //          to dial a digit.
    // str  ==> Current output string
    static void printStrings(String phNo, int i,
                    HashMap<Character, String> hm,
                    StringBuilder str)
    {
        // If all digits are processed, print output
        // string
        if (i == phNo.length())
        {
            System.out.print(str + " ");
            return;
        }
 
        // Get current digit of phNo, and recur for all
        // characters that can be used to dial it.
        String s = hm.get(phNo.charAt(i));
        for (int j = 0; j < s.length(); j++)
        {
            str.append(s.charAt(j));
            printStrings(phNo, i+1, hm, str);
            str.deleteCharAt(str.length()-1);
        }
    }
 
    // Prints all possible combinations of strings that
    // can be used to dial c[].
    static void printStringForNumber(String phNo)
    {
        // Create a HashMap
        HashMap<Character, String> hm =
                    new HashMap<Character, String>();
 
        // For every digit, store characters that can
        // be used to dial it.
        hm.put('2', "ABC");
        hm.put('3', "DEF");
        hm.put('4', "GHI");
        hm.put('5', "JKL");
        hm.put('6', "MNO");
        hm.put('7', "PQRS");
        hm.put('8', "TUV");
        hm.put('9', "WXYZ");
        hm.put('1', "1");
        hm.put('0', "0");
 
        // Create a string to store a particular output
        // string
        StringBuilder str = new StringBuilder();
 
        // Call recursive function
        printStrings(phNo, 0, hm, str);
    }
 
    // Driver code to test above methods
    public static void main(String args[])
    {
        // Prints
        printStringForNumber("23");
    }
}