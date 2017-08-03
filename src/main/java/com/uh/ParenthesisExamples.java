package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ParenthesisExamples {

    public static void main(String[] args) {

        boolean check = checkForBalancesPAranthesis("[(abc)]{d}{[(e)(f)](g)}[h]ijk");
        System.out.println(check);

        boolean dup = duplicateParenthesis("(((a+(b)))+(c+d))");
        System.out.println(dup);

        int depth = maxDepth("( a(b) (c) (d(e(f)g)h) I (j(k)l)m)");
        System.out.println(depth);

        List<Integer> list = allWaysParenthesization("5*4-3*2");
        System.out.println(Arrays.deepToString(list.toArray()));

        int len = longestValid("()(()))))");
        System.out.println(len);

        // BFS
        List<String> valid = removeInvalid("()())()");
        System.out.println(Arrays.deepToString(valid.toArray()));

        printParanthesis(3);

        int min = minReversals("}{{}}{{{");
        System.out.println(min);

        // DFS
        List<String> res = expressionAddOperators("123", 6);
        System.out.println(Arrays.deepToString(res.toArray()));
    }

    private static List<String> expressionAddOperators(String s, int sum) {

        List<String> res = new ArrayList<>();
        addDFS(s, 0, "", sum, res, 0, 0);
        return res;
    }

    private static void addDFS(String s, int i, String res, int sum, List<String> list, long preNum, long cursum) {
        if (i == s.length() && cursum == sum) {
            list.add(res);
            return;
        }

        if (i == s.length())
            return;

        for (int j = i; j < s.length(); j++) {
            String cur = s.substring(i, j + 1);

            if (cur.length() > 1 && cur.charAt(0) == '0')
                break;
            long curNum = Long.parseLong(cur);

            if (res.isEmpty()) {
                addDFS(s, j + 1, cur, sum, list, curNum, curNum);
            } else {
                addDFS(s, j + 1, res + "*" + curNum, sum, list, cursum - preNum + preNum * curNum, preNum * curNum);
                addDFS(s, j + 1, res + "+" + curNum, sum, list, cursum + curNum, curNum);
                addDFS(s, j + 1, res + "-" + curNum, sum, list, cursum - curNum, -curNum);
            }
        }
    }

    private static int minReversals(String s) {
        int n = s.length();
        if (n % 2 != 0)
            return -1;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '}' && !stack.isEmpty()) {

                if (stack.peek() == '{')
                    stack.pop();
                else
                    stack.push(ch);
            } else
                stack.push(ch);
        }

        int cl = 0;
        int op = 0;

        while (!stack.isEmpty()) {
            Character ch = stack.pop();
            if (ch == '{')
                op++;
            if (ch == '}')
                cl++;
        }

        return (int) (Math.ceil(op / 2) + Math.ceil(cl / 2));
    }

    private static void printParanthesis(int n) {

        char[] ch = new char[n * n];
        printParanthesis(n, 0, ch, 0, 0);
    }

    private static void printParanthesis(int n, int index, char[] ch, int open, int close) {

        if (close == n) {
            System.out.println(new String(ch));
            return;
        }

        if (open > close) {
            ch[index] = '}';
            printParanthesis(n, index + 1, ch, open, close + 1);
        }

        if (open < n) {
            ch[index] = '{';
            printParanthesis(n, index + 1, ch, open + 1, close);
        }

    }

    private static List<String> removeInvalid(String s) {
        List<String> res = new ArrayList<>();

        if (s.isEmpty())
            return res;
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        boolean level = true;
        String temp = "";
        q.add(s);
        set.add(s);

        while (!q.isEmpty()) {

            String sub = q.poll();
            if (isValid(sub)) {
                res.add(s);
                level = true;
            }

            if (level)
                continue;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (!(ch == '(' || ch == ')'))
                    continue;

                temp = s.substring(0, i) + s.substring(i + 1);
                if (!set.contains(temp)) {
                    set.add(temp);
                    q.add(temp);
                }
            }
        }

        return res;
    }

    private static boolean isValid(String s) {

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                count++;
            else if (ch == ')')
                count--;

            if (count < 0)
                return false;
        }

        return count == 0;
    }

    private static int longestValid(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                stack.pop();
                if (!stack.isEmpty())
                    max = Math.max(max, i - stack.peek());
                else
                    stack.push(i);

            }
        }
        return max;
    }

    private static List<Integer> allWaysParenthesization(String s) {

        Map<String, List<Integer>> map = new HashMap<>();

        return ways(s, map);
    }

    static boolean isOperator(char op) {
        return (op == '+' || op == '-' || op == '*');
    }

    private static List<Integer> ways(String s, Map<String, List<Integer>> map) {

        if (map.get(s) != null)
            return map.get(s);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {

                List<Integer> pre = ways(s.substring(0, i), map);
                List<Integer> post = ways(s.substring(i + 1), map);

                for (int j = 0; j < pre.size(); j++) {
                    for (int k = 0; k < post.size(); k++) {
                        if (ch == '+') {
                            res.add(pre.get(j) + post.get(k));
                        } else if (ch == '-') {
                            res.add(pre.get(j) - post.get(k));
                        } else if (ch == '*') {
                            res.add(pre.get(j) * post.get(k));
                        }
                    }
                }
            }

        }

        if (res.size() == 0)
            res.add(Integer.parseInt(s));

        map.put(s, res);
        // System.out.println(map);
        return res;

    }

    private static int maxDepth(String s) {

        int max = Integer.MIN_VALUE;
        int cur_max = 0;
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (ch == '(') {
                cur_max++;

                if (cur_max > max)
                    max = cur_max;

            } else if (ch == ')') {

                if (cur_max > 0)
                    cur_max--;
                else
                    return -1;
            }
        }

        if (max != Integer.MIN_VALUE)
            return max;
        return -1;
    }

    private static boolean duplicateParenthesis(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')') {

                Character top = stack.pop();

                if (top == '(')
                    return true;
                else {
                    while (top != '(')
                        top = stack.pop();
                }

            } else
                stack.push(ch);
        }
        return false;
    }

    private static boolean checkForBalancesPAranthesis(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.keySet().contains(ch)) {
                stack.push(ch);
            } else if (map.values().contains(ch)) {
                if (!stack.isEmpty() && map.get(stack.peek()) == ch)
                    stack.pop();
                else
                    return false;
            }
        }

        return stack.isEmpty();

    }

}
