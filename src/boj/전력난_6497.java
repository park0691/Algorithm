package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 전력난_6497 {

    static int[] parent;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if (V == 0 && E == 0) break;

            parent = new int[V];
            edges = new Edge[E];
            for (int i = 0; i < V; i++) parent[i] = i;
            int totalSum = 0;
            int minSum = 0;
            int cnt = 0;

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(v1, v2, c);
                totalSum += c;
            }

            Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);

            for (int i = 0; i < E; i++) {
                Edge e = edges[i];

                if (findParent(e.v1) == findParent(e.v2)) continue;

                union(e.v1, e.v2);
                cnt++;
                minSum += e.c;

                if (cnt == V - 1) break;
            }

            System.out.println(totalSum - minSum);
        }

    }

    static int findParent(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = findParent(parent[a]);
    }

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }

    static class Edge {
        int v1, v2, c;
        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
}