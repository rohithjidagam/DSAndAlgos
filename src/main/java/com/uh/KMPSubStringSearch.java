package com.uh;

public class KMPSubStringSearch {

    public static void main(String[] args) {

        String str = "abcxabcdabcdabcyabcdabcy";
        String subString = "abcdabcy";
        
        //O(n*m)
        long startTime = System.currentTimeMillis();
        int ind = search(str, subString);
        System.out.println(ind);
        System.out.println("Naive Approach Time: " + (System.currentTimeMillis() - startTime));
        
        //O(m+n)
        startTime = System.currentTimeMillis();
        int ind2 = KMPSearch(str, subString);
        System.out.println(ind2);
        System.out.println("KMP Approach Time: " + (System.currentTimeMillis() - startTime));
    }

    private static int KMPSearch(String S, String T) {

        int[] dp = build(T.toCharArray(), T.length());

        int i=0;
        int j=0;
        while(i < S.length() && j < T.length()){
            if(S.charAt(i) == T.charAt(j)){
                i++;
                j++;
            } else {
                if(j != 0){
                    j = dp[j-1];
                } else {
                    i++;
                }
            }
            
            if(j == T.length()){
                System.out.println("Pattern found at:" + (i-j));
                j = dp[j-1];
            }
        }
        
        return -1;
    }

    private static int[] build(char[] ch, int n) {

        int i = 1;
        int j = 0;
        
        int[] temp = new int[n];
        
        while(i < n){
            if(ch[i] == ch[j]){
                temp[i] = j + 1;
                j++;
                i++;
            } else {
                if(j != 0){
                    j = temp[j-1];
                } else {
                    temp[i] = 0;
                    i++;
                }
            }
        }
        return temp;
    }

    private static int search(String S, String T) {

        int i = 0;
        int j = 0;
        int k = 0;
        
        while(i < S.length() && j < T.length()){
            if(S.charAt(i) == T.charAt(j)){
                i++;
                j++;
            } else {
                j = 0;
                k++;
                i = k;
            }
            
            if(j == T.length()){
                return k;
            }
        }
        return -1;
    }

}
