package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ItenaryOfTickets {

	private static final String ORIGIN = "JFK";
	Map<String, List<String>> map = new HashMap<>();
	List<String> result = new ArrayList<>();
	int numTickets = 0;
	int numTicketsUsed = 0;

	public static void main(String[] args) {

		// origin is "JFK"
		String[][] tickets = { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" },
				{ "ATL", "SFO" } };

		ItenaryOfTickets i = new ItenaryOfTickets();

		List<String> out = i.itenary(tickets);
		System.out.println(out);
	}

	private List<String> itenary(String[][] tickets) {

		if (tickets == null || tickets.length == 0)
			return result;

		numTickets = tickets.length;

		// build graph
		for (int i = 0; i < tickets.length; i++) {
			if (map.containsKey(tickets[i][0])) {
				map.get(tickets[i][0]).add(tickets[i][1]);
			} else {
				List<String> list = new ArrayList<>();
				list.add(tickets[i][1]);
				map.put(tickets[i][0], list);
			}
		}

		// sort on lexograhical order
		for (Entry<String, List<String>> e : map.entrySet()) {
			Collections.sort(e.getValue());
		}

		result.add(ORIGIN);
		dfs(ORIGIN);
		return result;
	}

	private void dfs(String o) {

		if (!map.containsKey(o))
			return;

		List<String> list = map.get(o);
		for (int i = 0; i < list.size(); i++) {

			String neighbor = list.get(i);
			list.remove(i);
			result.add(neighbor);
			numTicketsUsed++;
			dfs(neighbor);

			if (numTickets == numTicketsUsed)
				return;

			list.add(i, neighbor);
			result.remove(list.size() - 1);
			numTicketsUsed--;
		}
	}

}
