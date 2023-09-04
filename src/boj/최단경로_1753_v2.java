package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class 최단경로_1753_v2 {

    static int cost[];
    static boolean visit[];
    static int V, E, K;
    static List<Edge>[] vertices;
    static Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        cost = new int[V + 1];
        visit = new boolean[V + 1];
        vertices = new ArrayList[V + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int i = 1; i <= V; i++) {
            vertices[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            vertices[u].add(new Edge(v, w));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (cost[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(cost[i] + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dijkstra() {
        cost[K] = 0;
        pq.offer(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (visit[e.end]) continue;
            visit[e.end] = true;

            for (Edge curEdge : vertices[e.end]) {
                if (cost[curEdge.end] > cost[e.end] + curEdge.weight) {
                    cost[curEdge.end] = cost[e.end] + curEdge.weight;
                    pq.offer(new Edge(curEdge.end, cost[e.end] + curEdge.weight));
                }
            }
        }
    }

    static class Edge {
        int end, weight;

        public Edge(int v, int weight) {
            this.end = v;
            this.weight = weight;
        }
    }
}
