package com.google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Merge Intervals Application.
 * 
 * @author rjidgam
 *
 */
public class BoldString {

	public static void main(String[] args) {

		String s = "abcxyz123";
		List<String> list = Arrays.asList("abc", "123");
		Interval[] inte = new Interval[list.size()];
		int k = 0;

		for (String string : list) {

			int ind = s.indexOf(string);
			if (ind != -1)
				inte[k++] = new Interval(ind, ind + string.length());
		}

		Arrays.sort(inte, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.st, o2.st);
			}
		});

		for (int i = 0; i < inte.length; i++) {
			
			System.out.println(inte[i]);
		}
		Stack<Interval> stack = new Stack<>();
		int i = 0;
		stack.push(inte[i]);
		i++;

		while (i < inte.length) {

			Interval cur = stack.peek();
			Interval next = inte[i];

			if (cur.end < next.st)
				stack.push(next);
			else {
				stack.pop();
				next.st = Math.min(next.st, cur.st);
				stack.push(next);
			}
			
			i++;

		}
		
		
		String sb = "";
		int st = 0;
		int prevSt = s.length();
		while(!stack.isEmpty()){
			
			sb = s.substring(stack.peek().end, prevSt) + sb;
			Interval in = stack.pop();
			prevSt = in.st;
			sb = "<b>" + s.substring(in.st, in.end) + "</b>" + sb;
			st = in.st;
		}
		
		sb = s.substring(0, st) + sb;
		
		System.out.println(sb);

	}

}

class Interval {
	int st;
	int end;

	public Interval(int i, int j) {
		st = i;
		end = j;
	}

	@Override
	public String toString() {
		return st + "-" + end;
	}
}