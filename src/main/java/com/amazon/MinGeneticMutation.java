package com.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MinGeneticMutation {

	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("a");
		list.add("a");
		set.addAll(list);

		String[] bank = { "AAAACCCC", "AAACCCCC", "AACCCCCC" };
		String start = "AAAAACCC";
		String end = "AACCCCCC";

		MinGeneticMutation m = new MinGeneticMutation();
		int minMutation = m.minMutation(start, end, bank);
		System.out.println(minMutation);
	}

	public int minMutation(String start, String end, String[] bank) {

		if (start.equals(end))
			return 0;

		char[] charSet = new char[] { 'A', 'C', 'G', 'T' };
		Queue<String> q = new LinkedList<>();
		Set<String> store = new HashSet<>();
		Set<String> vis = new HashSet<>();
		for (String s : bank) {
			store.add(s);
		}

		q.add(start);
		vis.add(start);

		int level = 0;
		while (!q.isEmpty()) {

			int n = q.size();

			while (n-- > 0) {

				String cur = q.poll();
				if (cur.equals(end))
					return level;

				char[] charArray = cur.toCharArray();
				for (int i = 0; i < charArray.length; i++) {

					char ch = charArray[i];
					for (int j = 0; j < charSet.length; j++) {
						charArray[i] = charSet[j];
						String next = new String(charArray);

						if (!vis.contains(next) && store.contains(next)) {
							q.add(next);
							vis.add(next);
						}
					}
					charArray[i] = ch;
				}
			}
			level++;
		}
		
		return -1;

	}

}
