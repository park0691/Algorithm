package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최소신장트리_3124_크루스칼 {
    static int T, V, E;
    static long sum;
    static int[] parent;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            sum = 0;
            parent = new int[V + 1];
            edges = new Edge[E];

            // 간선
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(v1, v2, c);
            }

            makeSet();
            // 간선 정렬
            Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);

            // 작은 것부터 => 맨 앞부터
            int cnt = 0;		// 간선의 개수, 정점이 V - 1개
            for (int i = 0; i < E; i++) {
                Edge e = edges[i];
                // 이미 연결되어 있는지 확인
                if (findSet(e.v1) == findSet(e.v2)) continue;
                union(e.v1, e.v2);
                cnt++;			// 선택된 간선의 개수
                sum += e.c;		// 선택된 비용의 누적 값

                if (cnt == V - 1) break;
            }

            System.out.println("#" + t + " " + sum);
        }
    }

    private static void makeSet() {
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
    }

    // path compression
    private static int findSet(int a) {
        if (a == parent[a]) return a;
        // 자신의 속한 집합의 대표자를 자신의 부모로 바꾼다.
        return parent[a] = findSet(parent[a]);
    }

    private static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px < py) parent[py] = px;
        else parent[px] = py;
    }

    static class Edge {
        int v1, v2, c;

        public Edge(int v1, int v2, int c) {
            super();
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
}
