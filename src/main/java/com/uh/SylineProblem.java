package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SylineProblem {

    /*
     * Source: http://www.zrzahid.com/the-skyline-problem/
     */
    public static void main(String[] args) {

        Building buildings[] = { new Building(1, 11, 5), new Building(2, 6, 7), new Building(3, 13, 9),
                new Building(12, 7, 16), new Building(14, 3, 25), new Building(19, 18, 22), new Building(23, 13, 29),
                new Building(24, 4, 28) };

        ArrayList<Integer[]> strips = skyLine(buildings);
        System.out.println(Arrays.deepToString(strips.toArray()));
    }

    private static ArrayList<Integer[]> skyLine(Building[] buildings) {
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();

        int n = buildings.length;
        if (n == 0)
            return result;

        Building[] start = Arrays.copyOf(buildings, n);
        Building[] end = Arrays.copyOf(buildings, n);
        Queue<Integer> maxHeap = new PriorityQueue(n, Collections.reverseOrder());

        Arrays.sort(start, new Comparator<Building>() {

            @Override
            public int compare(Building o1, Building o2) {

                int c = Integer.compare(o1.left, o2.left);
                if (c == 0) {
                    c = Integer.compare(o1.height, o2.height);
                }
                return c;
            }
        });

        Arrays.sort(end, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                return Integer.compare(o1.right, o2.right);
            }
        });

        int i = 0, j = 0;

        while (i < n || j < n) {
            if (i < n && start[i].left <= end[j].right) {
                maxHeap.add(start[i].height);
                int cur_max = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                result.add(new Integer[] { start[i].left, cur_max });
                i++;
            } else {
                maxHeap.remove(end[j].height);
                int cur_max = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                result.add(new Integer[] { end[j].right, cur_max });
                j++;
            }
        }

        ArrayList<Integer[]> merge = new ArrayList<Integer[]>();
        int prev = 0;
        for (Integer[] strip : result) {

            if (strip[0] == end[n - 1].right && strip[1] != 0)
                continue;

            if (prev == 0) {
                prev = strip[1];
                merge.add(strip);
            } else if (prev != strip[1]) {
                prev = strip[1];
                merge.add(strip);
            }

        }

        return merge;
    }

}

class Building {

    int left;
    int height;
    int right;

    public Building(int l, int h, int r) {
        left = l;
        height = h;
        right = r;
    }
}