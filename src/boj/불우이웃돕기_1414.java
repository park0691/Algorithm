package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 불우이웃돕기_1414 {

    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        List<Edge> edges = new ArrayList<>();
        int ans = -1;
        int totalCost = 0;
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (chars[j] == '0') continue;
                int c;
                if (chars[j] >= 'a') c = chars[j] - 'a' + 1;
                else c = chars[j] - 'A' + 27;
                edges.add(new Edge(i, j, c));
                totalCost += c;
            }
        }

        Collections.sort(edges, (e1, e2) -> e1.c - e2.c);
        int cost = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (findParent(e.v1) == findParent(e.v2)) continue;

            union(e.v1, e.v2);
            cnt++;
            cost += e.c;

            if (cnt == N - 1) break;
        }

        if (cnt == N - 1) ans = totalCost - cost;
        System.out.println(ans);
    }

    static class Edge {
        int v1, v2, c;
        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }

    static int findParent(int a) {
        if (parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
    }

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }
}
