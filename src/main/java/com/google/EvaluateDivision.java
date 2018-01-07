package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

	public static void main(String[] args) {

		String[][] equations = { { "a", "b" }, { "b", "c" } };
		double[] values = { 2.0, 3.0 };
		String[][] queries = { { "a", "c" }, { "b", "a" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
		EvaluateDivision e = new EvaluateDivision();
		double[] res = e.calcEquation(equations, values, queries);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}

	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

		Map<String, List<String>> pairs = new HashMap<String, List<String>>();
		Map<String, List<Double>> pairValues = new HashMap<>();

		for (int i = 0; i < equations.length; i++) {
			String[] eq = equations[i];
			if (!pairs.containsKey(eq[0])) {
				pairs.put(eq[0], new ArrayList<>());
				pairValues.put(eq[0], new ArrayList<>());
			}

			if (!pairs.containsKey(eq[1])) {
				pairs.put(eq[1], new ArrayList<>());
				pairValues.put(eq[1], new ArrayList<>());
			}

			pairs.get(eq[0]).add(eq[1]);
			pairs.get(eq[1]).add(eq[0]);
			pairValues.get(eq[0]).add(values[i]);
			pairValues.get(eq[1]).add(1 / values[i]);
		}
		
		System.out.println(pairs);
		System.out.println(pairValues);

		double[] res = new double[queries.length];

		for (int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			res[i] = dfs(query[0], query[1], pairs, pairValues, new HashSet<>(), 1.0);
			if (res[i] == 0.0)
				res[i] = -1.0;
		}
		return res;
	}

	private double dfs(String st, String end, Map<String, List<String>> pairs,
			Map<String, List<Double>> pairValues, HashSet set, double val) {

		if(set.contains(st)) return 0.0;
		if(!pairs.containsKey(st)) return 0.0;
		if(st.equals(end))
			return val;
		set.add(st);
		
		List<String> list = pairs.get(st);
		List<Double> values = pairValues.get(st);
		double temp = 0.0;
		for (int i = 0; i < list.size(); i++) {
			temp = dfs(list.get(i), end, pairs, pairValues, set, val * values.get(i));
			if(temp != 0.0) break;
		}
		
		set.remove(st);
		return temp;
	}

}
