package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_16236 {

    static int N;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Whale w;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        w = new Whale();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 9) {
                    w.y = i;
                    w.x = j;
                } else {
                    map[i][j] = n;
                }
            }
        }
        int ans = 0;
        while (true) {
            int cnt = bfs();
            if (cnt == 0) break;
            ans += cnt;
        }
        System.out.println(ans);
    }

    static int bfs() {
        boolean[][] visit = new boolean[N][N];
        int minDist = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        visit[w.y][w.x] = true;
        q.offer(new Node(w.y, w.x, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 먹을 수 있는 물고기 발견
            // pq 안쓰면 직접 탐색시 우선 순위 높은 좌표 값 찾아야 됨
            if (map[cur.y][cur.x] != 0 && map[cur.y][cur.x] < w.size) {
                if (cur.dist < minDist) {
                    minDist = cur.dist;
                    minY = cur.y;
                    minX = cur.x;
                } else if (cur.dist == minDist) {
                    if (cur.y == minY) {
                        minX = Math.min(cur.x, minX);
                    } else {
                        if (cur.y < minY) {
                            minX = cur.x;
                            minY = cur.y;
                        }
                    }
                }
            }

            // pruning
            if (cur.dist + 1 >= minDist) continue;

            // 이동 위치 탐색 (물고기 + 빈 칸)
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (visit[ny][nx] || map[ny][nx] > w.size) continue;
                visit[ny][nx] = true;
                q.offer(new Node(ny, nx, cur.dist + 1));
            }
        }

        if (minDist == Integer.MAX_VALUE) return 0;
        // 물고기 먹기 및 상어 이동
        map[minY][minX] = 0;
        w.y = minY;
        w.x = minX;
        // 상어 크기 증가
        if (++w.eatCnt == w.size) {
            w.size++;
            w.eatCnt = 0;
        }
        return minDist;
    }

    static class Whale {
        int y, x, eatCnt, size;
        public Whale() {
            this.size = 2;
        }
    }

    static class Node {
        int y, x, dist;
        public Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}
