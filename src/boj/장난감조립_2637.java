package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 장난감조립_2637 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<Part>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) adjList.add(new ArrayList<>());

        int[] inDegree = new int[N + 1];
        int[] count = new int[N + 1];
        boolean[] basicPart = new boolean[N + 1];
        Arrays.fill(basicPart, true);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            basicPart[X] = false;
            inDegree[Y]++;
            adjList.get(X).add(new Part(Y, K));
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        count[N] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Part adj : adjList.get(cur)) {
                int next = adj.num;
                int c = adj.cnt;
                count[next] += c * count[cur];
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (basicPart[i]) {
                System.out.println(i + " " + count[i]);
            }
        }
    }

    static class Part {
        int num, cnt;

        public Part(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
