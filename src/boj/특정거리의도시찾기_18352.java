package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 특정거리의도시찾기_18352 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] distance = new int[N + 1];
        List<List<Integer[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList.get(A).add(new Integer[] {B, 1});
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[X] = 0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{X, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (Integer[] adj : adjList.get(cur[0])) {
                int newDist = cur[1] + adj[1];

                if (newDist < distance[adj[0]]) {
                    distance[adj[0]] = newDist;
                    pq.offer(new int[] {adj[0], newDist});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                sb.append(i).append('\n');
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }
}
