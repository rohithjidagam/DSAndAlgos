package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Frequencies {

	public static void main(String[] args) {

		int arr[] = { -1, 1, 4, -4, 3, 5, 4, -2, 3, -1 };

		Map<Integer, Integer> map = new HashMap<>();

		Arrays.sort(arr);
		findFreq(arr, 0, arr.length - 1, map);

		System.out.println(map);

		List<Integer> list = new ArrayList<>();
		int k = 3;

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(map.entrySet());
		
		
		int i = 0;
		while (i < k) {
			list.add(pq.poll().getKey());
			i++;
		}

		System.out.println(list);
	}

	private static void findFreq(int[] a, int s, int e, Map<Integer, Integer> map) {

		if (a[s] == a[e]) {
			map.put(a[s], map.getOrDefault(a[s], 0) + e - s + 1);
		} else {
			int mid = (s + e) / 2;
			findFreq(a, s, mid, map);
			findFreq(a, mid + 1, e, map);
		}

	}

}
