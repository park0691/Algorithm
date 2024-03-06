package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 타임머신_11657 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] dist = new long[N + 1];
        boolean infinite = false;
        List<Pair>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[from].add(new Pair(to, c));
        }

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int n = 1; n <= N; n++) {
            for (int v = 1; v <= N; v++) {
                for (Pair p : adjList[v]) {
                    int dst = p.dst;
                    int cost = p.cost;

                    if (dist[v] != Long.MAX_VALUE && dist[v] + cost < dist[dst]) {
                        dist[dst] = dist[v] + cost;

                        if (n == N) {
                            infinite = true;
                        }
                    }
                }
            }
        }

        if (infinite) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) sb.append(dist[i] != Long.MAX_VALUE ? dist[i] : -1).append('\n');
            System.out.println(sb);
        }
    }

    static class Pair {
        int dst, cost;

        public Pair(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }
}
