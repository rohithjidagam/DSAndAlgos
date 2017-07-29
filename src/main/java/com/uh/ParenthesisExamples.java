package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
