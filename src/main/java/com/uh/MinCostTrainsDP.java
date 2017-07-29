package com.uh;

public class MinCostTrainsDP {

    private static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        int cost[][] = { { 0, 15, 80, 90 }, { INF, 0, 40, 50 }, { INF, INF, 0, 70 }, { INF, INF, INF, 0 } };

        int min = minCostRecur(cost, 0, cost.length - 1);
        System.out.println(min);
        
        
        int minDP = minCostDP(cost);
        System.out.println(minDP);
    }

    private static int minCostDP(int[][] cost) {

        int[] dist = new int[4];
        
        for (int k = 0; k < dist.length; k++) {
            dist[k] = INF;
        }
        
        dist[0] = 0;
        
        for (int i = 0; i < 4; i++) {
            
            for (int j = i+1; j < 4; j++) {
            
                if(dist[j] > dist[i]+cost[i][j])
                    dist[j] = dist[i]+cost[i][j];
            }
        }
        
        
        return dist[3];
    }

    private static int minCostRecur(int[][] cost, int s, int d) {

        if (s == d || 1 + s == d)
            return cost[s][d];

        int min = cost[s][d];

        for (int i = s + 1; i < d; i++) {

            int c = minCostRecur(cost, s, i) + minCostRecur(cost, i, d);
            if (c < min)
                min = c;
        }
        return min;

    }

}
