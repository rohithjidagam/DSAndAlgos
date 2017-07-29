package com.uh;

import java.util.ArrayList;
import java.util.List;

public class StringRearrangement {

    public static void main(String[] args) {

        String[] inputArray = { "abc", "abx", "axx", "abx", "abc" };
        StringRearrangement s = new StringRearrangement();
        System.out.println(s.stringsRearrangement(inputArray));
    }

    boolean stringsRearrangement(String[] inputArray) {


        return false;
    }

    boolean check(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
        }
        return (count == 1) ? true : false;
    }

}

class GNode {
    String s;
    List<GNode> adj;

}
