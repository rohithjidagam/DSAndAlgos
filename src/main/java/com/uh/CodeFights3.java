package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFights3 {

    public static void main(String[] args) {

        CodeFights3 cf = new CodeFights3();

        cf.numeral('G');

        cf.cipher26("taiaiaertkixquxjnfxxdh");

        cf.sixColumnEncryption("Meet me behind the kitchen tomorrow at seven in the evening");
    }

    private String sixColumnEncryption(String msg) {

        int l = msg.length();
        System.out.println(l);
        int n = msg.length() / 6;
        if (n % 6 != 0) {
            n = n + 1;
        }

        System.out.println(n);
        char[][] mat = new char[n][6];

        int k = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < 6; j++) {

                if (k >= l) {
                    mat[i][j] = '.';
                } else {
                    char c = msg.charAt(k);

                    if (c == ' ')
                        mat[i][j] = '.';
                    else
                        mat[i][j] = c;
                }
                k++;
            }

        }

        String res = "";

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < n; j++) {
                res += mat[j][i];
            }
            res += " ";
        }

        System.out.println(res.substring(0, res.length() - 1));

        return null;
    }

    private void cipher26(String s) {

        int sum = 0;
        int rem = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            sum += (s.charAt(i) - 'a');
            rem = sum % 26;
            res += (char) ('a' + (char) rem);
        }

        System.out.println(res);

    }

    private void numeral(char c) {

        int n = c - 'A';
        List<String> list = new ArrayList<>();
        int i = 0;
        int j = n;
        while (i <= j) {
            char c1 = (char) ((char) i + 'A');
            char c2 = (char) ((char) j + 'A');
            list.add("\"" + c1 + "+" + c2 + "\"");
            i++;
            j--;
        }

        String[] res = new String[list.size()];
        int k = 0;
        for (String s : list) {
            res[k++] = s;
        }

        System.out.println(Arrays.deepToString(res));
    }

}
