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

        System.out.println("*********");
        // BFS
        List<String> valid = removeInvalid("()()))()");
        System.out.println(Arrays.deepToString(valid.toArray()));
        System.out.println("*********");

        printParanthesis(3);

        int min = minReversals("}{{}}{{{");
        System.out.println(min);

        // DFS
        List<String> res = expressionAddOperators("123", 6);
        System.out.println(Arrays.deepToString(res.toArray()));

        int[] nums = { 7, 2, 5, 10, 8 };
        splitArray(nums, 2);
    }

    /*
     * The problem can be solved by using binary search, which is a quite
     * brilliant way. If m equals length of the array, the largest sum should be
     * the maximum among the elements. If m equals 1, then it should be the sum
     * of all elements in the array. Now the maximum sum of a subarray should be
     * between these two numbers.
     * 
     * The idea is to using binary search and find this minimum maximum sum. We
     * set left to the maximum element of the array and right to the sum of the
     * array. First we choose the mid of these two and find if there exist m
     * subarrays that have largest sum less than or equal to mid. If we can find
     * such split, we know we probably can do better. So we set right to mid. We
     * keep on doing this until we find a value that we cannot get by splitting
     * the array to m subarrays, i.e., the number is too small that we need to
     * split the array further more. Now we increase left to mid + 1. When left
     * = right, we find the number.
     * 
     * 
     * [7, 2, 5, 10, 8] m = 2
     * 
     * left = 10, right = 32, mid = 21 => [7, 2, 5], [10, 8]
     * 
     * left = 10, right = 21, mid = 15 => [7, 2], [10, 5],[8]
     * 
     * left = 16, right = 21, mid = 18 => [7, 2], [10, 8]
     * 
     * left = 16, right = 18, mid = 17 => [7, 2], [10, 5],[8]
     * 
     * left = 18, right = 18 => return 18
     * 
     * http://shirleyisnotageek.blogspot.com/2016/10/split-array-largest-sum.html
     */
    private static void splitArray(int[] nums, int m) {

        int sum = 0;
        int max = -1;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if (m == nums.length) {
            System.out.println(max);
            return;
        }
        if (m == 1) {
            System.out.println(sum);
            return;
        }

        int l = max;
        int r = sum;

        while (l < r) {
            int mid = (l + r) / 2;
            if (isValid(mid, nums, m))
                r = mid;
            else
                l = mid + 1;
        }

        System.out.println(l);
    }

    public static boolean isValid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<String> expressionAddOperators(String num, int target) {

        List<String> list = new ArrayList<>();
        add(list, "", num, target, 0, 0, 0);
        return list;
    }

    /**
     * 
     * @param list
     * @param res
     * @param input
     * @param target
     * @param pos
     * @param curVal
     * @param lastVal
     */
    static void add(List<String> list, String res, String input, int target, int pos, long curVal, long lastVal) {

        if (pos == input.length()) {
            if (curVal == target) {
                list.add(res);
            }
            return;
        }

        for (int i = pos; i < input.length(); i++) {

            if (i != pos && input.charAt(pos) == '0')
                break;

            String sub = input.substring(pos, i + 1);
            Long cur = Long.parseLong(sub);

            if (pos == 0) {
                add(list, res + sub, input, target, i + 1, cur, cur);
            } else {
                add(list, res + "+" + sub, input, target, i + 1, curVal + cur, cur);
                add(list, res + "-" + sub, input, target, i + 1, curVal - cur, -cur);
                add(list, res + "*" + sub, input, target, i + 1, curVal - lastVal + lastVal * cur, lastVal * cur);
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
        List<String> res = new ArrayList<String>();

        if (s == null)
            return res;
        Queue<String> q = new LinkedList<String>();
        Set<String> set = new HashSet<String>();
        boolean level = false;
        q.add(s);
        set.add(s);

        while (!q.isEmpty()) {

            s = q.poll();
            if (isValid(s)) {
                res.add(s);
                level = true;
            }

            if (level)
                continue;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (ch != '(' && ch != ')')
                    continue;

                String temp = s.substring(0, i) + s.substring(i + 1);
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
