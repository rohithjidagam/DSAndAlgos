package com.uh;

public class Boggle {

    static int M = 3;
    static int N = 3;
    static String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO", "SEEK", "GIESEK" };
    
    static int P = 3;
    static int Q = 3;
    static String word = "FFEF";

    public static void main(String[] args) {

        char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' } };

        findWords(boggle);
        
       // char mat[][] = {{ 'X', 'F', 'E', 'E' }, { 'F', 'X', 'F', 'F' }, { 'F', 'F', 'X', 'E' }, { 'E', 'F', 'E', 'X' }};
        
        char mat[][] = { { 'X', 'F', 'E' }, { 'F', 'X', 'F' }, { 'E', 'F', 'X' } };
        
        findWord(mat);
    }

    private static void findWord(char[][] mat) {

        boolean[][] visited = new boolean[P][Q];

        String str = "";
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < Q; j++) {
                if (!visited[i][j] && mat[i][j] != 'X')
                    findWordUtil(mat, visited, i, j, str);
            }

        }
    }
    
    private static void findWordUtil(char[][] mat, boolean[][] visited, int i, int j, String str) {

        visited[i][j] = true;
        str += mat[i][j];

        if (word.equals(str)){
            System.out.println(str);
        }

        for (int k = i - 1; k <= i + 1 && k < P; k++) {
            for (int l = j - 1; l <= j + 1 && l < Q; l++) {
                if (k >= 0 && l >= 0 && !visited[k][l] && mat[k][l] != 'X')
                    findWordUtil(mat, visited, k, l, str);
            }
        }

       str = str.substring(0, str.length() - 1);
        visited[i][j] = false;

    }

    private static void findWords(char[][] boggle) {

        boolean[][] visited = new boolean[M][N];

        String str = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j])
                    boggle(boggle, visited, i, j, str);
            }

        }
    }

    private static void boggle(char[][] boggle, boolean[][] visited, int i, int j, String str) {

        visited[i][j] = true;
        str += boggle[i][j];

        if (isWord(str))
            System.out.println(str);

        for (int k = i - 1; k <= i + 1 && k < M; k++) {
            for (int l = j - 1; l <= j + 1 && l < N; l++) {
                if (k >= 0 && l >= 0 && !visited[k][l])
                    boggle(boggle, visited, k, l, str);
            }
        }

        // back track
        visited[i][j] = false;

    }

    private static boolean isWord(String str) {

        for (int i = 0; i < dictionary.length; i++) {
            if (str.equals(dictionary[i]))
                return true;
        }
        return false;
    }

}
