package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 음악프로그램_2623 {
    static int N, M;
    static int[] indegree;
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();
        indegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String x = br.readLine();
            st = new StringTokenizer(x);
            int n = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 1; j < n; j++) {
                int cur = Integer.parseInt(st.nextToken());
                adjList.get(before).add(cur);
                indegree[cur]++;
                before = cur;
            }
        }

        topological_sort();
    }

    static void topological_sort() {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);

            for (Integer v : adjList.get(node)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        if (result.size() != N) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i) + "\n");
        }
        System.out.println(sb.toString());
    }
}
