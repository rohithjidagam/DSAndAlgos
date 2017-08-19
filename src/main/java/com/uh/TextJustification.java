package com.uh;

public class TextJustification {

    public static void main(String[] args) {

        String[] words = { "Tushar", "Roy", "likes", "to", "code" };
        int L = 10;

        int n = words.length;

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = L - words[i].length();
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] - words[j].length() - 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dp[i][j] < 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = (int) Math.pow(dp[i][j], 2);
                }
            }
        }

        int minCost[] = new int[words.length];
        int result[] = new int[words.length];
        for (int i = words.length - 1; i >= 0; i--) {
            minCost[i] = dp[i][words.length - 1];
            result[i] = words.length;
            for (int j = words.length - 1; j > i; j--) {
                if (dp[i][j - 1] == Integer.MAX_VALUE) {
                    continue;
                }
                if (minCost[i] > minCost[j] + dp[i][j - 1]) {
                    minCost[i] = minCost[j] + dp[i][j - 1];
                    result[i] = j;
                }
            }
        }

        int i = 0, j;
        StringBuilder builder = new StringBuilder();
        do {
            j = result[i];
            for (int k = i; k < j; k++) {
                builder.append(words[k] + " ");
            }
            builder.append("\n");
            i = j;
        } while (j < words.length);

        System.out.println(builder.toString());

        System.out.println("Min Cost is:" + minCost[0]);

        for (int k = 0; k < minCost.length; k++) {
            System.out.print(minCost[k] + " ");
        }
        System.out.println();

        print(dp);

    }

    private static void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

}
