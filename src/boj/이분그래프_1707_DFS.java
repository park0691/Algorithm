package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프_1707_DFS {
    static final int MAX_V = 20001;
    static int K, V, E;
    static char[] group;
    static boolean bipartite;
    static List<List<Integer>> vertex = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());

        for (int v = 0; v < MAX_V; v++) vertex.add(new ArrayList<>());

        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for (int v = 1; v <= V; v++) vertex.get(v).clear();
            group = new char[V + 1];

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                vertex.get(v1).add(v2);
                vertex.get(v2).add(v1);
            }

            bipartite = true;

            for (int v = 1; v <= V; v++) {
                if (group[v] != '\u0000') continue;
                dfs(v, 'A');
                if (!bipartite) break;
            }

            System.out.println(bipartite ? "YES" : "NO");
        }
    }

    static void dfs(int v, char g) {
        group[v] = g;

        for (int nv : vertex.get(v)) {
            if (group[nv] != '\u0000') {
                if (group[nv] == group[v]) {
                    bipartite = false;
                    break;
                }
            } else {
                char ng = group[v] == 'A' ? 'B' : 'A';
                dfs(nv, ng);
            }
        }
    }
}