package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 효율적인해킹_1325 {
    // 임의 사용자 정의 클래스 생성시 시간 초과 안 됨
    // ArrayList로 변경 시 시간 초과
    // List<Integer>[]로 변경 시 시간 초과
    // BFS 시도시 시간 초과
    static Computer[] coms;
    static boolean[] visit;
    static int[] hackCounts;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        coms = new Computer[N + 1];
        hackCounts = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            coms[i] = new Computer(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 신뢰 컴퓨터 추가
            // DFS
            coms[b].adjList.add(coms[a]);
            // BFS
//            coms[a].adjList.add(coms[b]);
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visit, false);
            visit[i] = true;
            dfs(i, i);
//            bfs(i);
        }

        int maxHackCount = 0;
        for (int i = 1; i <= N; i++) {
            maxHackCount = Math.max(maxHackCount, hackCounts[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (hackCounts[i] == maxHackCount) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    static void dfs(int original, int now) {
        for (Computer adjC : coms[now].adjList) {
            if (visit[adjC.idx]) continue;
            visit[adjC.idx] = true;
            hackCounts[original]++;
            dfs(original, adjC.idx);
        }
    }

    static void bfs(int v) {
        Queue<Computer> q = new LinkedList<>();
        visit[v] = true;
        q.offer(coms[v]);

        while (!q.isEmpty()) {
            Computer n = q.poll();

            for (Computer adj : coms[n.idx].adjList) {
                if (visit[adj.idx]) continue;
                hackCounts[adj.idx]++;
                visit[adj.idx] = true;
                q.offer(adj);
            }
        }
    }

    static class Computer {
        int idx;
        List<Computer> adjList;

        public Computer(int idx) {
            this.idx = idx;
            adjList = new ArrayList<>();
        }
    }
}