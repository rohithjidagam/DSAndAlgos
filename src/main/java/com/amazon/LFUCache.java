package com.amazon;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache {

	Map<Integer, Integer> valMap;
	Map<Integer, Integer> countMap;
	Map<Integer, Set<Integer>> keys;
	int cap;
	int min;

	public LFUCache(int c) {
		min = -1;
		cap = c;
		valMap = new HashMap<>();
		countMap = new HashMap<>();
		keys = new HashMap<>();
		keys.put(1, new LinkedHashSet<>());
	}

	public static void main(String[] args) {

		LFUCache cache = new LFUCache(2);
		System.out.println("here");
		cache.set(1, 1);
		cache.set(2, 2);
		System.out.println(cache.keys);
		cache.get(3);
		
		System.out.println(cache.keys);

	}

	private int get(int key) {

		if(!valMap.containsKey(key))
			return -1;
		
		int count = countMap.get(key);
		countMap.put(key, count+1);
		keys.get(count).remove(key);
		if(keys.get(count).size() == 0)
			min++;
		
		if(!keys.containsKey(count + 1))
			keys.put(count + 1, new LinkedHashSet<>());

		keys.get(count+1).add(key);
		return valMap.get(key);
	}

	private void set(int key, int value) {

		if(cap <=0)
			return;
		if(valMap.containsKey(key)){
			valMap.put(key, value);
			get(key);
			return;
		}
		
		if(valMap.size() >= cap){
			int evit = keys.get(min).iterator().next();
			keys.get(min).remove(evit);
			valMap.remove(evit);
			countMap.remove(evit);
		}
		
		valMap.put(key, value);
		countMap.put(key, 1);
		min = 1;
		keys.get(min).add(key);
	}

}
