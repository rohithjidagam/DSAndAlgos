package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestChain {

	public static void main(String[] args) {

		List<String> dict = new ArrayList<>();
		dict.add("POON");
		dict.add("PLEE");
		dict.add("SAME");
		dict.add("POIE");
		dict.add("PLEA");
		dict.add("PLIE");
		dict.add("POIN");

		String start = "TOON";
		String end = "PLEA";

		wordLadder1(dict, start, end);
		dict.clear();
		dict.add("POON");
		dict.add("PLEE");
		dict.add("SAME");
		dict.add("POIE");
		dict.add("PLEA");
		dict.add("PLIE");
		dict.add("POIN");

		wordLadder3(dict, start, end);

		dict.clear();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");

		start = "hit";
		end = "cog";

		List<List<String>> wordLadder2 = wordLadder2(dict, start, end);
		for (List<String> list : wordLadder2) {
			System.out.println(list);
		}

	}

	private static void wordLadder3(List<String> dict, String start, String end) {

		Queue<Word> q = new LinkedList<>();
		dict.add(end);
		q.add(new Word(1, start));

		while (!q.isEmpty()) {

			Word cur = q.poll();
			int d = cur.dist;
			System.out.println(cur.word);
			if (cur.word.equals(end)) {
				System.out.println(cur.dist);
				break;
			}

			char[] charArray = cur.word.toCharArray();

			for (int i = 0; i < charArray.length; i++) {

				for(char c = 'A' ;c <= 'Z' ;c++){
					char ch = charArray[i];
					if(ch != c){
						charArray[i] = c;
						String s = new String(charArray);
						if(dict.contains(s)){
							q.add(new Word(d+1, s));
							dict.remove(s);
						}
					}
					charArray[i] = ch;
				}
			}

		}

	}

	private static List<List<String>> wordLadder2(List<String> dict, String start, String end) {

		List<List<String>> result = new ArrayList<>();
		dict.add(end);

		Deque<List<String>> paths = new LinkedList<>();
		List<String> pathI = new LinkedList<>();
		pathI.add(start);
		paths.add(pathI);

		int level = 1;
		int lastLevel = Integer.MAX_VALUE;
		Set<String> wordsPerLevel = new HashSet<>();

		while (!paths.isEmpty()) {
			List<String> path = paths.pollFirst();

			if (path.size() > level) {
				dict.removeAll(wordsPerLevel);
				wordsPerLevel.clear();
				level = path.size();
				if (level > lastLevel)
					break;
			}

			String last = path.get(level - 1);
			char[] chars = last.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				char ch = chars[i];
				for (char c = 'a'; c <= 'z'; c++) {
					chars[i] = c;
					String next = new String(chars);
					if (dict.contains(next)) {
						wordsPerLevel.add(next);
						List<String> nextPath = new LinkedList<>(path);
						nextPath.add(next);
						if (next.equals(end)) {
							result.add(nextPath);
							lastLevel = level;
						} else {
							paths.addLast(nextPath);
						}
					}
				}
				chars[i] = ch;
			}
		}

		return result;
	}

	private static void wordLadder1(List<String> dict, String start, String end) {
		Queue<Word> q = new LinkedList<>();

		q.add(new Word(1, start));
		while (!q.isEmpty()) {

			Word st = q.poll();
			Word e = null;
			Iterator<String> it = dict.iterator();
			while (it.hasNext()) {
				String w = it.next();
				if (isAdjacent(st.word, w)) {
					e = new Word(st.dist + 1, w);
					q.add(e);
					it.remove();
					if (w == end) {
						System.out.println(e.dist);
						break;
					}
				}
			}

		}
	}

	private static boolean isAdjacent(String start, String w) {

		int count = 0;

		if (start.length() != w.length())
			return false;

		for (int i = 0; i < start.length(); i++) {
			if (start.charAt(i) != w.charAt(i))
				count++;
		}
		return count == 1 ? true : false;
	}

}

class Word {
	String word;
	int dist;
	Word prev;

	Word(int l, String s) {
		word = s;
		dist = l;
	}

	Word(int l, String s, Word p) {
		word = s;
		dist = l;
		prev = p;
	}
}
