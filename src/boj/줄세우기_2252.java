package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 줄세우기_2252 {

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
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
            indegree[B]++;
        }

        topological_sort();
    }

    static void topological_sort() {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.offer(node);

            for (Integer v : adjList.get(node)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        while (!result.isEmpty()) {
            System.out.print(result.poll() + " ");
        }
        System.out.println();
    }
}
