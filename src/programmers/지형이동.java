package programmers;

import java.util.*;
import java.util.Queue;

public class 지형이동 {
    static int N, groupCnt;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] parent;

    static public int solution(int[][] land, int height) {
        N = land[0].length;
        int[][] group = bfs(land, height);
        Queue<Ladder> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
//        Map<Pos, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny >= N || nx >= N || ny < 0 || nx < 0 || group[ny][nx] == group[i][j]) continue;
                    int cost = Math.abs(land[ny][nx] - land[i][j]);
                    pq.offer(new Ladder(cost, group[ny][nx], group[i][j]));

                    // Map에 최소 비용만 담기 (SOL 2)
                    // Pos cur = new Pos(group[ny][nx], group[i][j]);
                    // if (map.get(cur) == null) {
                    //     map.put(cur, cost);
                    // } else {
                    //     int curCost = map.get(cur);
                    //     if (cost < curCost) {
                    //         map.put(cur, cost);
                    //     }
                    // }
                }
                // 하
//                 int ny = i + 1;
//                 int nx = j;

//                 if (group[ny][nx] != group[i][j]) {
//                     int cost = Math.abs(land[ny][nx] - land[i][j]);
//                     // pq.offer(new Ladder(cost, group[ny][nx], group[i][j]));
//                     Pos cur = new Pos(group[ny][nx], group[i][j]);
//                     if (map.get(cur) == null) {
//                         map.put(cur, cost);
//                     } else {
//                         int curCost = map.get(cur);
//                         if (cost < curCost) {
//                             map.put(cur, cost);
//                         }
//                     }
//                 }

//                 if (j == N - 1) continue;

                // 우
//                 ny = i;
//                 nx = j + 1;

//                 if (group[ny][nx] != group[i][j]) {
//                     int cost = Math.abs(land[ny][nx] - land[i][j]);
//                     // pq.offer(new Ladder(cost, group[ny][nx], group[i][j]));
//                     Pos cur = new Pos(group[ny][nx], group[i][j]);
//                     if (map.get(cur) == null) {
//                         map.put(cur, cost);
//                     } else {
//                         int curCost = map.get(cur);
//                         if (cost < curCost) {
//                             map.put(cur, cost);
//                         }
//                     }
//                 }
            }
        }

        // Map에 최소 비용만 담기 (SOL 2)
        // for (Pos p : map.keySet()) {
        //     pq.offer(new Ladder(map.get(p), p.y, p.x));
        // }

        int connectCount = 0;
        int minCost = 0;
        parent = new int[groupCnt + 1];
        for (int i = 1; i <= groupCnt; i++) parent[i] = i;

        while (connectCount < groupCnt - 1) {
            Ladder cur = pq.poll();
            if (findParent(cur.a) == findParent(cur.b)) continue;
            union(cur.a, cur.b);
            minCost += cur.cost;
            connectCount++;
        }

        return minCost;
    }

    static int findParent(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = findParent(parent[a]);
    }

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }

    static public int[][] bfs(int[][] land, int height) {
        int[][] group = new int[N][N];
        Queue<Pos> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (group[i][j] != 0) continue;
                q.offer(new Pos(i, j));
                group[i][j] = ++groupCnt;
                while (!q.isEmpty()) {
                    Pos cur = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur.y + dy[d];
                        int nx = cur.x + dx[d];

                        if (ny >= N || nx >= N || ny < 0 || nx < 0 || group[ny][nx] != 0) continue;
                        if (Math.abs(land[cur.y][cur.x] - land[ny][nx]) > height) continue;
                        group[ny][nx] = groupCnt;
                        q.offer(new Pos(ny, nx));
                    }
                }
            }
        }

        return group;
    }

    static class Pos {
        int y, x;
        Pos(int y, int x) {
            this.y = y; this.x = x;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pos)) return false;
            Pos tmp = (Pos) other;
            return (this.y == tmp.y && this.x == tmp.x) || (this.y == tmp.x && this.x == tmp.y);
        }
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static class Ladder {
        int cost, a, b;
        Ladder(int cost, int a, int b) {
            this.cost = cost;
            this.a = a; this.b = b;
        }
    }

    public static void main(String[] args) {
        int[][] e1 = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};

        System.out.println(solution(e1, 3));
    }
}
