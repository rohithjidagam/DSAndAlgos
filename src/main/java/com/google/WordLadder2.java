package com.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {

	public static void main(String[] args) {

		String begin = "hit";
		String end = "cog";

		String[] w = { "hot", "dot", "dog", "lot", "log" };

		List<String> words = new ArrayList<>();
		words.add(begin);
		words.add(end);
		words.addAll(Arrays.asList(w));

		Map<String, List<String>> graph = new HashMap<>();

		for (int i = 0; i < words.size(); i++) {
			String node = words.get(i);
			for (int j = 0; j < words.size(); j++) {
				String child = words.get(j);
				if (i != j && isAdj(node, child)) {
					List<String> list = graph.get(node);
					if (list == null)
						list = new ArrayList<>();
					list.add(child);
					graph.put(node, list);
				}
			}
		}
		
		System.out.println(graph);
		
		//bidirectionalBFS(begin, end, graph);
		
		Queue<LadderNode> queue = new LinkedList<>();
		queue.add(new LadderNode(begin, 0));
		
		while(!queue.isEmpty()){
			
			LadderNode child = queue.poll();
			List<String> list = graph.get(child.s);
			for (String c : list) {
				
				if(child.vis.contains(c))
					continue;
				
				if(c.equals(end)){
					System.out.println("Done");
				}
				
				LadderNode node = new LadderNode(c, child.d+1);
				
				
				child.vis.add(c);
				node.vis.add(child.s);
				node.prev = child;
				
				System.out.println(child.s + "--" + c + "--" + child.vis);
				
				queue.add(node);
				
			}
			
		}
		
		

	}

	private static void bidirectionalBFS(String begin, String end, Map<String, List<String>> graph) {
		Queue<LadderNode> startQ = new LinkedList<>();
		startQ.add(new LadderNode(begin, 0));
		
		Queue<LadderNode> endQ = new LinkedList<>();
		endQ.add(new LadderNode(end, 0));
		
		int i = 0;
		while(!startQ.isEmpty() && !endQ.isEmpty()){
			
			LadderNode src = startQ.poll();
			LadderNode dest = endQ.poll();
			List<String> child = graph.get(src.s);
			for(String s : child){
				if((src.prev != null && s.equals(src.prev.s)) || src.vis.contains(s))
					continue;
				if(dest.vis.contains(s)){
					printPath(src, dest, s);
				}
				LadderNode c = new LadderNode(s, src.d + 1);
				
				c.prev = src;
				startQ.add(c);
				src.vis.add(s);
				c.vis.add(src.s);
			}
			
			List<String> child2 = graph.get(dest.s);
			for(String s : child2){
				if((dest.prev != null && s.equals(dest.prev.s)) || dest.vis.contains(s))
					continue;
				if(dest.prev != null)
				System.out.println(dest.s + "--"  + s + "--" + dest.prev.s);
				if(src.vis.contains(s)){
					printPath(src, dest, s);
				}
				LadderNode c = new LadderNode(s, dest.d + 1);
				c.prev = dest;
				endQ.add(c);
				dest.vis.add(s);
				c.vis.add(dest.s);
			}
			
			System.out.println("*************");
			
		//	System.out.println(src.vis);
		//	System.out.println(dest.vis);
			
			
			if(i == 3)
				break;
			
			i++;
			
			
		}
	}

	private static void printPath(LadderNode src, LadderNode end, String s) {

		List<String> path = new ArrayList<>();
		while(src!= null){
			path.add(src.s);
			src = src.prev;
		}
		
		Collections.reverse(path);
		path.add(s);
		
		while(end!= null){
			path.add(end.s);
			end = end.prev;
		}
		
		System.out.println(path);
		
		
		
	}

	private static boolean isAdj(String s1, String s2) {

		if (s1.length() != s2.length())
			return false;

		int count = 0;
		for (int i = 0; i < s1.length(); i++) {

			if (s1.charAt(i) != s2.charAt(i))
				count++;
		}

		return count == 1;
	}

}

class LadderNode{
	String s;
	int d;
	Set<String> vis;
	LadderNode prev;
	public LadderNode(String s, int d){
		this.s = s;
		this.d = d;
		vis = new HashSet<>();
		prev = null;
	}
}
