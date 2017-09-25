package com.google;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestWordInDict {

    public static void main(String[] args) {

        String[] d = { "ale", "apple", "monkey", "plea" };
        String s = "abpcplea";

        // sorting dict
        String longest = findLongestWord(s, d);
        System.out.println(longest);

        // without sorting
        String longest2 = findLongestWord2(s, d);
        System.out.println(longest2);
    }

    private static String findLongestWord2(String s, String[] d) {
        List<String> list = Arrays.asList(d);
        String longest = "";
        
        for (String st : list) {
            
            int i = 0;
            for(char ch : s.toCharArray()){
                if(i < st.length() && ch == st.charAt(i))
                    i++;
            }
            
            if(i == st.length() && st.length() >= longest.length()){
                if(st.length() > longest.length() || st.compareTo(longest) < 0)
                    longest = st;
            }
        }
        
        return longest;
    }

    private static String findLongestWord(String s, String[] d) {

        List<String> list = Arrays.asList(d);
        Collections.sort(list,
                (a, b) -> a.length() != b.length() ? Integer.compare(b.length(), a.length()) : a.compareTo(b));

        for (String st : list) {

            int i = 0;
            for (char ch : s.toCharArray()) {
                if (i < st.length() && ch == st.charAt(i))
                    i++;
            }

            if (i == st.length())
                return st;

        }
        return "";
    }

}
