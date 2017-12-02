package com.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintComments {

	public static void main(String[] args) {

		List<String> lines = new ArrayList<>();
		readInput(lines);

		System.out.println(lines);
		List<String> removeComments = removeComments(lines);
		System.out.println(removeComments);

	}

	private static List<String> removeComments(List<String> source) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		boolean mode = false;
		for (String s : source) {
			for (int i = 0; i < s.length(); i++) {
				if (mode) {
					if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
						mode = false;
						sb.append(s.substring(i, i+2));
					}
					if (mode)
						sb.append(s.charAt(i));
				} else {
					if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
						sb.append(s.substring(i));
						break;
					} else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
						mode = true;
						sb.append(s.substring(i));
						break;
					} 
						
				}
			}
			if (sb.length() > 0) {
				res.add(sb.toString());
				sb = new StringBuilder(); 
			}
		}
		return res;
	}
	
	private static void readInput(List<String> lines) {
		try {
			Scanner s = new Scanner(System.in);

			while (true) {
				String line = s.nextLine();
				line = line.trim();
				if (line == null || line.length() == 0) {
					break;
				}
				lines.add(line);
			}
		} catch (Exception e) {
			System.err.println("Error while reading input");
		}
	}

}
