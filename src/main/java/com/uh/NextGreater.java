package com.uh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class NextGreater {

    public static void main(String[] args) {

        int[] a = { 4, 5, 2, 25 };

        int n = a.length;

        nextGreater(a, n);
        
        nextGreaterUsingMap(a, n);
    }

	private static void nextGreaterUsingMap(int[] a, int n) {

		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		Set<Character> set = new HashSet<>();

		for (int i = 0; i < a.length; i++) {
			while(!stack.isEmpty() && stack.peek() < a[i]){
				map.put(stack.pop(), a[i]);
			}
			stack.push(a[i]);
		}
		
		while(!stack.isEmpty()){
			map.put(stack.pop(), -1);
		}
		
		System.out.println(map);
	}

	private static void nextGreater(int[] a, int n) {
		Stack<Integer> s = new Stack<>();

        s.push(a[0]);

        int cur, next;

        for (int i = 1; i < n; i++) {
            next = a[i];

            if (!s.isEmpty()) {
                cur = s.pop();

                while (cur < next) {
                    System.out.println(cur + "-->" + next);
                    if (s.isEmpty())
                        break;
                    cur = s.pop();
                }

                if (cur > next) {
                    s.push(cur);
                }
            }
            s.push(next);
        }

        while (!s.isEmpty()) {
            System.out.println(s.pop() + "-->" + -1);

        }
	}

}
