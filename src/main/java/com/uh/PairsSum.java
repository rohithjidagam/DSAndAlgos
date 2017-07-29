package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class PairsSum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        pairsSum();

        printAllSubArrays();

        tripletZero();

        // int[][] sub = subsets();

        PairsSum sum = new PairsSum();
        int[] arr = { 10, 1, 2, 3, 4, 5, 6, 1 };
        boolean flag = sum.almostIncreasingSequence(arr);
        System.out.println(flag);

        String s = "";
        for (int i = 0; i < 100000; i++) {
            // s += "CodefightsIsAwesome";
        }
        long start = System.currentTimeMillis();
        // int k = subStr(s, "IsA");
        System.out.println(System.currentTimeMillis() - start);
        // System.out.println(k);

        String[] a = { "aba", "aa", "ad", "vcd", "aba" };
        String[] b = sum.allLongestStrings(a);
        System.out.println(Arrays.deepToString(b));

        List<String> al = new ArrayList<>();
        al.toArray();

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> keySet = map.keySet();
        for (Integer integer : keySet) {

        }

        String st = reverseBracksStack("a(bc(de)f)g");
        System.out.println(st);

        int[] A = { 1, 2, 3, 2, 2, 3, 3, 33 };
        int[] B = { 1, 2, 2, 2, 2, 3, 2, 33 };

        System.out.println(sum.isIPv4Address("2.2.34"));

        Integer[] AR = { 5, 3, 6, 7, 9 };
        int h = sum.avoidObstacles(AR);
        System.out.println(h);

        int[][] image = { { 36, 0, 18, 9 }, { 27, 54, 9, 0 }, { 81, 63, 72, 45 } };
        int[][] im = { { 7, 4, 0, 1 }, { 5, 6, 2, 2 }, { 6, 10, 7, 8 }, { 1, 4, 2, 0 } };
        int[][] res = sum.boxBlur(im);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

        boolean[][] ms = { { true, false, false, true }, { false, false, true, false }, { true, true, false, true } };
        int[][] mines = sum.minesweeper(ms);
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[0].length; j++) {
                System.out.print(mines[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(sum.variableName("var_1__Int"));

        System.out.println(sum.chessBoard());

        System.out.println(sum.depositProfit(100, 20, 170));

        String[] inputArray = { "abc", "abx", "axx", "abx", "abc" };
        System.out.println(sum.stringsRearrangement(inputArray));

        int[][] queens = { { 1, 1 }, { 3, 2 } };
        int[][] queries = { { 1, 1 }, { 0, 3 }, { 0, 4 }, { 3, 4 }, { 2, 0 }, { 4, 3 }, { 4, 0 } };
        boolean[] r = sum.queensUnderAttack(5, queens, queries);

    }
    
    String lineEncoding(String s) {
        
        int j=1;
        String res = "";
      
        for(int i=1;i<s.length();i++){
            
            if(s.charAt(i) == s.charAt(i-1)){
                j++;
            } else{
                if(j>1){
                    res+=j;
                }
                res+=s.charAt(i-1);
                j=1;
            }
            if(i == s.length()-1){
                if(j>1)
                    res+=j;
                res+=s.charAt(i);
            }
                
          
        }
        
        
        return res;

    }


    
    

    private boolean[] queensUnderAttack(int n, int[][] queens, int[][] queries) {

        HashMap<Integer, Integer> map = new HashMap<>();
        
        boolean[] res = new boolean[queries.length];
        
        

        int k, l;
        for (int i = 0; i < queens.length; i++) {

            System.out.println(i);
            int x = queens[i][0];
            int y = queens[i][1];

            k = 0;
            while (k < n && k >= 0) {
                map.put(x, k++);
            }

//            k = 0;
//            while (k < n && k >= 0) {
//                map.put(k++, y);
//            }
//
//            k = x - 1;
//            l = y - 1;
//            while (k < n && k >= 0 && l < n && l >= 0) {
//                map.put(k--, l--);
//            }
//
//            k = x + 1;
//            l = y + 1;
//            while (k < n && k >= 0 && l < n && l >= 0) {
//                map.put(k++, l++);
//            }
//
//            k = x + 1;
//            l = y - 1;
//            while (k < n && k >= 0 && l < n && l >= 0) {
//                map.put(k++, l--);
//            }
//
//            k = x - 1;
//            l = y + 1;
//            while (k < n && k >= 0 && l < n && l >= 0) {
//                map.put(k--, l++);
//            }
//            

        }
        System.out.println(map);
        return null;
    }

    boolean stringsRearrangement(String[] inputArray) {

   /*     boolean[] vis = new boolean[inputArray.length];
        boolean flag = true;
        int count = 0;
        int k = 0;
        for (int i = 0; i < inputArray.length; i++) {
            k = i;
            for (int j = 0; j < vis.length; j++) {
                if (i == j)
                    continue;

                flag = check(inputArray[i], inputArray[j]);

                if (flag) {
                    count++;
                    if (i < j)
                        i = j;
                }

                if (!flag)
                    continue;

            }
            i = k;
            if (count == inputArray.length - 1)
                return true;
            System.out.println("*******************");
            count = 0;

        }*/

        return false; 
        
    }

    
    void swap(int i, int j, String[] ar){
        String tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }



    boolean check(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
        }
        return (count == 1) ? true : false;
    }

    int depositProfit(int deposit, int rate, int threshold) {

        int count = 0;

        double dep = (double) deposit;
        double val = (double) rate / 100;

        while (dep < threshold) {
            dep += dep * val;
            count++;
        }

        return count;

    }

    private boolean chessBoard() {

        String[][] chess = new String[8][8];
        boolean[][] vis = new boolean[8][8];
        int m = chess.length;
        int n = chess[0].length;
        char ch = 'a';
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = ch + "" + (j + 1);
            }
            ch = (char) (ch + 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        String cell1 = "a1";
        String cell2 = "c3";

        Queue<Chess> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cell1.equals(chess[i][j])) {
                    q.add(new Chess(i, j));
                    vis[i][j] = true;
                    break;
                }
            }

        }

        while (!q.isEmpty()) {
            Chess poll = q.poll();
            int i = poll.i;
            int j = poll.j;

            if (cell2.equals(chess[i][j])) {
                return true;
            }

            if (isValid(i - 1, j - 1, m, n) && !vis[i - 1][j - 1]) {
                vis[i - 1][j - 1] = true;
                q.add(new Chess(i - 1, j - 1));
            }

            if (isValid(i - 1, j + 1, m, n) && !vis[i - 1][j + 1]) {
                vis[i - 1][j + 1] = true;
                q.add(new Chess(i - 1, j + 1));
            }

            if (isValid(i + 1, j - 1, m, n) && !vis[i + 1][j - 1]) {
                vis[i + 1][j - 1] = true;
                q.add(new Chess(i + 1, j - 1));
            }
            if (isValid(i + 1, j + 1, m, n) && !vis[i + 1][j + 1]) {
                vis[i + 1][j + 1] = true;
                q.add(new Chess(i + 1, j + 1));
            }
        }

        return false;

    }

    boolean variableName(String name) {

        char[] charArray = name.toCharArray();

        char s = name.charAt(0);
        if (s >= 0 && s <= 9)
            return false;

        for (int i = 0; i < name.length(); i++) {

            char ch = name.charAt(i);

            if (!isValid(ch))
                return false;

        }

        return true;

    }

    boolean isValid(char c) {

        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || (c >= '0' && c <= '9');

    }

    private int[][] minesweeper(boolean[][] ms) {

        int m = ms.length;
        int n = ms[0].length;

        int[][] res = new int[m][n];
        int p = 0;
        int q = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int sum = checkAdj(i, j, m, n, ms);
                res[p][q++] = sum;
                if (q == n) {
                    p++;
                    q = 0;
                }

            }
        }

        return res;
    }

    private int checkAdj(int i, int j, int m, int n, boolean[][] ms) {
        int sum = 0;
        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                if (l == i && k == j)
                    continue;
                else {
                    boolean check = isValidMS(l, k, m, n, ms);
                    if (check) {
                        sum++;
                    }
                }
            }
        }
        return sum;

    }

    private boolean isValidMS(int l, int k, int m, int n, boolean[][] ms) {
        return l >= 0 && k >= 0 && l < m && k < n && ms[l][k];
    }

    int[][] boxBlur(int[][] image) {

        int m = image.length;
        int n = image[0].length;

        int[][] res = new int[m - 2][n - 2];
        int p = 0;
        int q = 0;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int sum = checkAllAdjacent(i, j, m, n, image);
                if (sum != -1) {
                    res[p][q++] = sum;
                    if (q == n - 2) {
                        p++;
                        q = 0;
                    }
                }
            }

        }

        return res;

    }

    private int checkAllAdjacent(int i, int j, int m, int n, int[][] image) {
        boolean flag = true;
        int sum = 0;
        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                boolean check = isValid(l, k, m, n);
                flag = flag & check;
                if (flag) {
                    sum += image[l][k];
                }
            }
        }

        if (flag)
            return sum / 9;
        return -1;

    }

    private boolean isValid(int l, int k, int m, int n) {
        return l >= 0 && k >= 0 && l < m && k < n;
    }

    private int avoidObstacles(Integer[] a) {

        List<Integer> set = Arrays.asList(a);

        int max = Integer.MIN_VALUE;

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i > max) {
                max = i;
            }
        }

        int jump = 0;
        boolean flag = false;
        int k = 1;
        while (!flag) {
            jump = 0;
            for (int i = 0; i < max; i++) {
                jump += k;
                if (set.contains(jump))
                    break;
                if (i == max - 1)
                    flag = true;
            }
            k++;
        }

        return k - 1;

    }

    boolean isIPv4Address(String inputString) {

        StringTokenizer st = new StringTokenizer(inputString, ".");
        int count = 0;
        System.out.println(st.countTokens());
        if (st.countTokens() != 4)
            return false;
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            try {
                int num = Integer.parseInt(nextToken);
                if (num >= 0 && num <= 255) {
                    count++;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return (count == 4) ? true : false;

    }

    private static String reverseBracksStack(String s) {

        Stack<Character> stack = new Stack();
        String st;
        for (int i = 0; i < s.length(); i++) {
            st = "";
            if (s.charAt(i) == ')') {

                char c = stack.pop();
                while (c != '(') {
                    st += c;
                    c = stack.pop();
                }
                if (c == '(') {
                    for (int j = 0; j < st.length(); j++) {
                        stack.push(st.charAt(j));
                    }
                }

            } else
                stack.push(s.charAt(i));
        }

        StringBuffer res = new StringBuffer();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }

    String[] allLongestStrings(String[] inputArray) {

        int max = Integer.MIN_VALUE;
        int j = 0;
        for (int i = 0; i < inputArray.length; i++) {
            int l = length(inputArray[i]);
            if (l >= max) {
                max = l;
                j++;
            }
        }

        String[] res = new String[j];
        j = 0;
        for (int i = 0; i < inputArray.length; i++) {
            int l = length(inputArray[i]);
            if (l == max) {
                res[j++] = inputArray[i];
            }

        }

        return res;

    }

    int length(String s) {
        return s.length();
    }

    private static int subStr(String s, String x) {
        int k = x.length();
        int hash = hash(x);

        for (int i = 0; i <= s.length() - k; i++) {
            String d = s.substring(i, i + k);
            int h = hash(d);
            if (h == hash) {
                int j = i;
                int m = 0;
                int l = 0;
                int n = k + j;
                while (j < n) {
                    if (s.charAt(j) == x.charAt(m))
                        l++;
                    j++;
                    m++;
                }
                if (l == k)
                    return i;
            }
        }
        return -1;
    }

    static int hash(String x) {
        int count = 0;
        for (int i = 0; i < x.length(); i++) {
            count += x.charAt(i) - 'a';
        }
        return count;
    }

    boolean almostIncreasingSequenceAA(int[] A) {

        if (A == null || A.length < 2) {
            return true;
        }

        int len = A.length;
        int remIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (A[i + 1] - A[i] <= 0) {
                remIndex = i;
            }
        }

        if (remIndex == -1) {
            return true;
        }
        if (remIndex > 0 && remIndex < len - 1 && A[remIndex - 1] >= A[remIndex + 1]) {
            remIndex = remIndex + 1;
        }
        for (int i = remIndex; i < len - 1; i++) {
            A[i] = A[i + 1];
        }
        len--;

        if (len < 2) {
            return true;
        }

        remIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (A[i + 1] - A[i] <= 0) {
                remIndex = i;
            }
        }

        if (remIndex == -1) {
            return true;
        }

        return false;
    }

    boolean almostIncreasingSequence(int[] sequence) {

        int k = fail(sequence);
        if (k == -1) {
            return true;
        }
        return ch(sequence, k) || ch(sequence, k - 1);
    }

    int fail(int[] s) {
        for (int i = 1; i < s.length; i++) {
            if (s[i] <= s[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    boolean ch(int[] s, int k) {
        int j = 1;
        if (k == 0) {
            j = 2;
        }
        for (int i = j; i < s.length; i++) {
            if (i != k) {
                if (i - 1 == k) {
                    if (s[i] <= s[k - 1]) {
                        return false;
                    }
                } else if (s[i] <= s[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] subsets() {

        int[] arr = { 1, 2, 3, 4, 5 };
        int sum = 5;

        int n = arr.length;
        Integer[][] res = new Integer[1 << n][];

        int k = 0;
        for (int i = 0; i < (1 << n); i++) {

            int[] a = new int[n];
            for (int j = 0; j < n; j++) {

                if ((i & (1 << j)) > 0) {
                    a[i] = arr[j];
                }
                // res[k][] = a[i];

            }

        }

        for (Integer[] is : res) {
            System.out.println(Arrays.deepToString(is));
        }

        return null;
    }

    private static void tripletZero() {

        int[] arr = { 0, -1, 2, -3, 1 };

        int n = arr.length;

        for (int i = 0; i < arr.length - 1; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < arr.length; j++) {

                int sum = arr[i] + arr[j];

                if (set.contains(-sum)) {
                    System.out.println(arr[i] + "+" + arr[j] + "+" + -sum + "=" + "0");
                } else {
                    set.add(arr[j]);
                }
            }
        }
    }

    private static void printAllSubArrays() {

        int arr[] = { 6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7 };

        List<Pair> pairs = new ArrayList<Pair>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (sum == 0) {
                Pair p = new Pair(0, i);
                pairs.add(p);
            }

            if (map.containsKey(sum)) {
                List<Integer> list = map.get(sum);
                for (Integer j : list) {
                    pairs.add(new Pair(j + 1, i));
                }
            }

            List<Integer> list = null;
            if (map.get(sum) == null) {
                list = new ArrayList<>();
            } else {
                list = map.get(sum);
            }
            list.add(i);
            map.put(sum, list);
        }

        for (Pair pair : pairs) {
            System.out.println(pair.i + "-" + pair.j);
        }

    }

    private static void pairsSum() {
        int[] arr = { 1, 5, 7, -1, 5, 3, 3 };

        Map<Integer, Integer> map = new HashMap<>();
        int SUM = 6;
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], 1);
            else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += map.get(SUM - arr[i]);

            if (SUM - arr[i] == arr[i]) {
                sum--;
            }

        }

        System.out.println(sum / 2);
    }

}

class Pair {
    int i, j;

    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Chess {
    public Chess(int i2, int j2) {
        i = i2;
        j = j2;
    }

    int i;
    int j;
}
