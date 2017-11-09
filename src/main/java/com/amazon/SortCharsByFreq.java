package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SortCharsByFreq {

	public static void main(String[] args) {

		String s = "Accbbaa";

		String res = sort(s);
		System.out.println(res);

		String res2 = sortBucket(s);
		System.out.println(res2);
	}

	private static String sortBucket(String s) {
		if (s == null || s.length() == 0)
			return "";
		Map<Character, Integer> map = new TreeMap<>();
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			map.put(ch[i], map.getOrDefault(ch[i], 0) + 1);
		}

		List<Character>[] bucket = new List[ch.length + 1];
		for (char c : map.keySet()) {
			int f = map.get(c);
			if (bucket[f] == null)
				bucket[f] = new ArrayList<>();
			bucket[f].add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = bucket.length - 1; i >= 0; i--) {
			if (bucket[i] != null) {
				for (char c : bucket[i]) {
					for (int j = 0; j < map.get(c); j++)
						sb.append(c);
				}
			}
		}

		return sb.toString();
	}

	private static String sort(String s) {

		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
				(a, b) -> b.getValue() == a.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
		pq.addAll(map.entrySet());

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Entry<Character, Integer> entry = pq.poll();
			for (int i = 0; i < entry.getValue(); i++) {
				sb.append(entry.getKey());
			}
		}

		return sb.toString();
	}

}
