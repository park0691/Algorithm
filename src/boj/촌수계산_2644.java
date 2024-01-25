package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 촌수계산_2644 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] visit = new boolean[N + 1];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            adjList.get(n1).add(n2);
            adjList.get(n2).add(n1);
        }

        int ans = -1;
        Queue<Integer[]> q = new LinkedList<>();
        visit[a] = true;
        q.offer(new Integer[]{a, 0});

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();

            if (cur[0] == b) {
                ans = cur[1];
                break;
            }

            for (int adj : adjList.get(cur[0])) {
                if (visit[adj]) continue;
                visit[adj] = true;
                q.offer(new Integer[]{adj, cur[1] + 1});
            }
        }

        System.out.println(ans);
    }
}
