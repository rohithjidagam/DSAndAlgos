package com.uh;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class CodeFights {

    public static void main(String[] args) {

        CodeFights cf = new CodeFights();
        System.out.println(cf.moduloHarshad(1, 5));

        System.out.println(cf.findNumber(6));

        List<Integer> li = new ArrayList<>();
        List<Integer>[] lis = new ArrayList[10];

        int[] a = { 24, 12, 15, 15, 19 };
        Arrays.asList(1).size();
        int[] arr = cf.longest_increasing_subsequence(a);

        cf.pairsSum();

        System.out.println(cf.reverseSentence("I LoVE You Very Muchh1!"));

        int n = 5;

        System.out.println(cf.happyNumber(19));

        int[] ias = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(Arrays.deepToString(cf.extractEachKth(ias, 3)));

        System.out.println(cf.amendTheSentence("CodefightsIsAwesome"));
        // int[] inta = { 5, 3, 5, 10, 1, 10, 10 };
        int[] inta = { 5, 3, 2, 9, 5, 4, 9, 5, 5 };
        System.out.println(cf.isoscelesTriangles(inta));

        System.out.println(cf.longestWord("Ready, steady, go!"));

        int[][] grid = { { 1, 2, 1 }, { 2, 2, 2 }, { 2, 2, 2 }, { 1, 2, 3 }, { 2, 2, 1 } };

        int count = cf.differentSquares(grid);
        System.out.println(count);

        System.out.println(cf.messageFromBinaryCode("010010000110010101101100011011000110111100100001"));
        int[][] points = { { 1, 1 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 1, 3 }, { 2, 5 }, { 1, 5 }, { -1, -1 }, { -1, -2 },
                { -2, -3 }, { -4, -4 } };
        int po = cf.visiblePoints(points);
        System.out.println(po);

        String[] words = { "This", "is", "an", "example", "of", "text", "justification." };
        System.out.println(cf.textJustification(words, 16));

        System.out.println(cf.danceSteps("695996177559"));

        System.out.println(cf.excelColumnName(702));

        System.out.println(cf.excelColumnNumber("ZZ"));

        cf.financialCrisis();

        int[][] graph = { { 0, 2, 2, 0 }, { 2, 0, 0, 2 }, { 2, 0, 0, 2 }, { 0, 2, 2, 0 } };
        //start,finish,weight,addLimit,graph
        //return shortest path and no. of paths from s -> f
        cf.shortestPathWithManyEdges(0,3,4,1,graph);

    }

    private void shortestPathWithManyEdges(int s, int f, int w, int l, int[][] graph) {

        AllPairsShortestPath shortestPath = new AllPairsShortestPath();
        int[][] dist = shortestPath.shortestPath(graph);
        
        int n = graph.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, Math.min(dist[s][f], Math.min(dist[s][i] + w, dist[i][f] + w)));
        }
        
        System.out.println(min);
        
        
    }

    private void financialCrisis() {

        boolean[][] roadRegister = { { false, true, true, false }, { true, false, true, false },
                { true, true, false, true }, { false, false, true, false } };
        int m = roadRegister.length;
        int n = roadRegister[0].length;
        boolean[][][] res = new boolean[m][n][];

        for (int k = 0; k < m; k++) {

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (i == 0 || j == 0)
                        continue;

                    //res[i][j][k] = roadRegister[i][j];

                }
            }

        }

    }

    private int excelColumnNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26;
            int n = s.charAt(i) - 'A' + 1;
            num += n;
        }
        return num;
    }

    private String excelColumnName(int n) {

        String s = "";
        int r;
        while (n > 0) {
            r = n % 26;
            if (r == 0) {
                s = "Z" + s;
                n = n / 26 - 1;
            } else {
                char c = (char) ((r - 1) + 'A');
                s = c + s;
                n = n / 26;
            }
        }
        return s;
    }

    int danceSteps(String s) {

        int c = 0;
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            res[i] = s.charAt(i) - '0';
        }

        int j = 0;
        int k = 0;
        int a = 0;
        int n = s.length();
        while (j < n) {
            if (res[k] != 0) {
                if (res[k] % 2 == 0) {
                    a += c;
                    c += res[k];
                    if (c >= n) {
                        a += n - 1;
                        break;
                    }
                } else {
                    a += c;
                    c -= res[k];
                    if (c < 0) {
                        a += n - 1;
                        break;
                    }
                }
                k = res[k];
            }
            j++;
        }

        return a;

    }

    String[] textJustification(String[] words, int L) {

        String[] res = new String[3];
        int len = 0;
        for (int i = 0; i < words.length; i++) {
            len += (words[i].length());
        }

        return res;

    }

    private int visiblePoints(int[][] points) {

        double[] angles = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            angles[i] = getAngle(points[i][0], points[i][1]);
        }

        Arrays.sort(angles);

        for (int i = 0; i < angles.length; i++) {
            System.out.print(angles[i] + " ");
        }
        double val = angles[0] + 45.0;
        int c = 0;
        for (int i = 0; i < angles.length; i++) {
            if (angles[i] > val) {
                c = i;
                break;
            }
        }

        System.out.println(c);

        int count;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < angles.length; i++) {
            count = 0;
            for (int j = 0; j < angles.length; j++) {
                if (angles[j] >= angles[i] && angles[j] <= angles[i] + 45.0) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }

        return max;
    }

    private double getAngle(int x2, int y2) {
        double d = Math.toDegrees(Math.atan2(y2, x2));
        return Math.round((d + 360) % 360);
    }

    String messageFromBinaryCode(String code) {

        int i = 0;
        int k = 8;
        String res = "";
        while (i < code.length()) {
            res += binToInt(code.substring(i, k));
            i = k;
            k = k + 8;
        }

        return res;

    }

    char binToInt(String s) {

        int sum = 0;
        int k = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int ch = s.charAt(i) - '0';
            sum += ch * Math.pow(2, k++);
        }
        char res = (char) sum;
        return res;

    }

    int differentSquares(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Set<Square> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                checkSquare(i, j, set, m, n, grid);
            }
        }
        return set.size();
    }

    private void checkSquare(int x, int y, Set<Square> set, int m, int n, int[][] grid) {

        if (isValidSquare(x, y, m, n) && isValidSquare(x, y + 1, m, n) && isValidSquare(x + 1, y + 1, m, n)
                && isValidSquare(x + 1, y, m, n)) {
            Square s = new Square(grid[x][y], grid[x + 1][y + 1], grid[x][y + 1], grid[x + 1][y]);
            if (!set.contains(s))
                set.add(s);
        }

    }

    private boolean isValidSquare(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

    String longestWord(String text) {

        int len = 0;
        int max = Integer.MIN_VALUE;
        String s = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (isValid(ch)) {
                len++;
            } else {
                if (max < len) {
                    max = len;
                    s = text.substring(i - len, i);
                }
                len = 0;
            }
        }

        return s;

    }

    boolean isValid(char c) {
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    int isoscelesTriangles(int[] a) {

        int c = 0;
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] < a[i + 1])
                c++;
            if (a[i] == a[i + 1])
                count++;
        }

        if (c == n - 1)
            return 0;
        if (count == n - 1)
            return (n * (n - 1) * (n - 2)) / 6;

        Isos[] res = new Isos[(n * (n - 1)) / 2];
        int k = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    if (a[i] == a[j] && i < j) {
                        res[k++] = new Isos(i, j, a[i] + a[j]);
                    }
                }
            }

        }

        for (int i = 0; i < res.length; i++) {
            if (res[i] != null)
                System.out.println(res[i]);
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < res.length; i++) {
            if (res[i] == null)
                break;
            if (map.get(res[i].sum) == null) {
                map.put(res[i].sum, 1);
            } else {
                map.put(res[i].sum, map.get(res[i].sum) + 1);
            }
        }

        Set<Integer> keySet = map.keySet();
        int max = Integer.MIN_VALUE;
        for (Integer integer : keySet) {
            if (map.get(integer) > max) {
                max = map.get(integer);
            }
        }

        int sum = 0;
        int p = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == null)
                break;
            Isos is = res[i];
            for (int j = 0; j < n; j++) {
                if (is.i != j && is.j != j && is.sum - a[j] != a[j]) {
                    if (is.sum > a[j]) {
                        sum++;
                    }
                }

                if (is.sum == a[j] + a[j] && is.i != j) {
                    p++;
                }

            }

        }
        System.out.println(p);
        System.out.println(sum);

        max = max - 2;

        return sum + max;

    }

    String amendTheSentence(String s) {

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isCap(ch)) {
                if (i != 0) {
                    res += " ";
                }
                char c = (char) (ch - 'A' + 'a');
                res += c;

            } else
                res += ch;
        }
        return res;

    }

    boolean isCap(char c) {
        return c >= 65 && c < 91;
    }

    Integer[] extractEachKth(int[] inputArray, int k) {

        int n = inputArray.length;

        int m = n / k;

        int l = n - m;

        Integer[] res = new Integer[l];

        int j = -1;
        int d = k;
        int q = 1;
        for (int i = 0; i < inputArray.length; i++) {

            if (i != d - 1) {
                res[++j] = inputArray[i];
            } else {
                q++;
                d = q * k;
            }
        }

        return res;

    }

    private boolean happyNumber(int n) {

        int k = n;

        int count = 0;

        while (n != 0) {
            n = n / 10;
            count++;
        }

        int[] num = new int[count];
        int i = 0;
        int r = 0;
        n = k;
        while (n != 0) {
            r = n % 10;
            num[i++] = r;
            n = n / 10;
        }

        int sum = 0;
        for (int j = 0; j < num.length; j++) {
            sum += num[j] * num[j];
        }

        System.out.println(k + "--" + sum);

        if (sum == 1) {
            return true;
        } else {

        }
        return false;

    }

    String reverseSentence(String sentence) {

        sentence = reverse(sentence.substring(0, sentence.length()));

        StringTokenizer st = new StringTokenizer(sentence, " ");
        StringBuffer s = new StringBuffer();
        while (st.hasMoreTokens()) {
            s.append(reverse(st.nextToken()));
            s.append(" ");
        }

        return s.toString();
    }

    String reverse(String s) {
        char[] c = s.toCharArray();
        int i = 0;
        int j = c.length - 1;
        while (i < j) {
            char ch = c[i];
            c[i] = c[j];
            c[j] = ch;
            i++;
            j--;

        }

        return new String(c);

    }

    private void pairsSum() {

        int[] arr = { 10, 9, 3, 8, 0, 4, 10, 5, -10, -3 };

        int zc = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                zc++;
        }

        if (zc == arr.length - 1)
            System.out.println((zc * (zc - 1)) / 2);

        int s = 0;
        int count = 0;
        int l = arr.length;
        int[] res = new int[((l * (l - 1)) / 2)];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (i == j)
                    continue;
                res[s++] = arr[i] + arr[j];
            }
        }

        for (int i = 0; i < res.length; i++) {

            for (int j = 0; j < arr.length; j++) {
                if (res[i] == arr[j])
                    count++;
            }

        }

        System.out.println(count);
    }

    int[] longest_increasing_subsequence(int[] input_array) {

        List<Integer>[] lis = new ArrayList[input_array.length];

        for (int i = 0; i < lis.length; i++) {
            lis[i] = new ArrayList<>();
        }

        lis[0].addAll(Arrays.asList(input_array[0]));

        for (int i = 1; i < input_array.length; i++) {

            for (int j = 0; j < i; j++) {

                if (input_array[i] > input_array[j] && lis[i].size() < lis[j].size() + 1) {
                    lis[i] = lis[j];
                }

            }

            lis[i].add(input_array[i]);

        }

        List<Integer> max = lis[0];

        for (List<Integer> x : lis)
            if (x.size() > max.size())
                max = x;

        int[] res = new int[max.size()];
        int j = 0;
        for (Integer i : max) {
            res[j++] = i;
        }

        return res;
    }

    private int findNumber(int i) {

        int n = i * 3;
        int m = n - 1;
        int l = n - 2;
        return i == 0 ? 0 : Integer.parseInt(l + "" + m + "" + n);
    }

    long moduloHarshad(long num1, long num2) {

        long len = Math.max(num1, num2);

        long i = 0;
        int num = 1;
        int n1 = 0;
        int n2 = 0;
        while (i < len) {
            int sum = sumOfDigits(num);
            if (num % sum == 0) {
                i++;

                if (i == num1) {
                    n1 = num;
                }

                if (i == num2) {
                    n2 = num;
                }
            }

            num++;

        }

        System.out.println(n1 + "--" + n2);

        return n2 % n1;

    }

    int sumOfDigits(int n) {
        int sum = 0;

        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

}

class Isos {
    int i;
    int j;
    int sum;

    public Isos(int x, int y, int s) {
        i = x;
        j = y;
        sum = s;

    }

    @Override
    public String toString() {
        return sum + "-" + i + "-" + j;
    }
}

class Square {
    int i;
    int j;
    int k;
    int l;

    public Square(int x, int y, int p, int q) {
        i = x;
        j = y;
        k = p;
        l = q;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Square)) {
            return false;
        }
        Square s = (Square) obj;
        return this.i == s.i && this.j == s.j && this.k == s.k && this.l == s.l;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((i == 0) ? 0 : 987654);
        long temp;
        temp = Double.doubleToLongBits(j);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((k == 0) ? 0 : 987654);
        result = prime * result + ((l == 0) ? 0 : 987654);
        return result;
    }

}