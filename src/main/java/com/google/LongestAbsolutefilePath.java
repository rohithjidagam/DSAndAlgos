package com.google;

import java.util.HashMap;

public class LongestAbsolutefilePath {

    public static void main(String[] args) {

        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 0);
        int result = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            int len = s.substring(level).length();
            if (s.contains(".")) {
                result = Math.max(result, hashMap.get(level) + len);
            } else {
                hashMap.put(level + 1, hashMap.get(level) + len + 1);
            }
        }
        
        System.out.println(result);
    }

}
