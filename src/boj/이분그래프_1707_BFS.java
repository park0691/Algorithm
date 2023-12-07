package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프_1707_BFS {
    static final int MAX_V = 20001;
    static List<List<Integer>> adjList;
    static char[] group;
    static boolean bipartite;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        adjList = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());

        for (int v = 0; v < MAX_V; v++) adjList.add(new ArrayList<>());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            bipartite = true;
            group = new char[V + 1];

            for (int v = 1; v <= V; v++) adjList.get(v).clear();

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                adjList.get(v1).add(v2);
                adjList.get(v2).add(v1);
            }

            for (int v = 1; v <= V; v++) {
                if (group[v] != '\u0000') continue;
                bfs(v);
                if (!bipartite) break;
            }

            System.out.println(bipartite ? "YES" : "NO");
        }

    }

    static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        group[v] = 'A';

        while (!q.isEmpty()) {
            int cv = q.poll();
            char nextGroup = group[cv] == 'A' ? 'B' : 'A';

            for (int av : adjList.get(cv)) {
                if (group[av] == '\u0000') {
                    group[av] = nextGroup;
                    q.offer(av);
                } else {
                    if (group[av] != nextGroup) {
                        bipartite = false;
                        return;
                    }
                }
            }
        }
    }
}
