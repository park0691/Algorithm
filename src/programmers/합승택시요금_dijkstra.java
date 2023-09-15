package programmers;

import java.util.*;

public class 합승택시요금_dijkstra {
    class Edge {
        int v, cost;
        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    Queue<Edge> pq = new PriorityQueue<>((v1, v2) -> v1.cost - v2.cost);
    ArrayList<Edge>[] map;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] costs = new int[3][n + 1];
        for (int i = 0; i < costs.length; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < fares.length; i++) {
            map[fares[i][0]].add(new Edge(fares[i][1], fares[i][2]));
            map[fares[i][1]].add(new Edge(fares[i][0], fares[i][2]));
        }

        dijkstra(s, costs[0]);
        dijkstra(a, costs[1]);
        dijkstra(b, costs[2]);

        for (int i = 1; i <= n; i++) {
            // System.out.println("#" + i);
            if (costs[0][i] == Integer.MAX_VALUE || costs[1][i] == Integer.MAX_VALUE || costs[2][i] == Integer.MAX_VALUE) continue;
            // System.out.printf("s -> %d = %d | %d -> a = %d | %d -> b = %d\n", i, costs[0][i],i,  costs[1][i],i, costs[2][i]);
            answer = Math.min(answer, costs[0][i] + costs[1][i] + costs[2][i]);
        }

        return answer;
    }

    void dijkstra(int s, int[] costs) {
        costs[s] = 0;
        pq.offer(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            for (Edge adj : map[e.v]) {
                int v = adj.v;
                int c = adj.cost;
                int curCost = costs[e.v] + c;
                if (curCost < costs[v]) {
                    costs[v] = curCost;
                    pq.offer(new Edge(v, curCost));
                }
            }
        }
        //System.out.println(Arrays.toString(costs));
    }
}
