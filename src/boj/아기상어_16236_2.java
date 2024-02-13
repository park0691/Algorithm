package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_16236_2 {

    static int N;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Whale w;
    static Queue<Node> pq = new PriorityQueue<>((a, b) ->
            a.dist == b.dist ? (
                    a.y == b.y ? (a.x - b.x) : (a.y - b.y)
            ) : (
                    a.dist - b.dist
            )
    );
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

        while (true) {
            bfs();
            if (pq.size() == 0) break;
            else {
                // 물고기 먹기 및 상어 이동
                Node fish = pq.poll();
                map[fish.y][fish.x] = 0;
                w.y = fish.y;
                w.x = fish.x;
                // 상어 크기 증가
                w.eat();
                // 초 증가
                w.sec += fish.dist;
            }
        }
        System.out.println(w.sec);
    }

    static void bfs() {
        boolean[][] visit = new boolean[N][N];
        Queue<Node> q = new LinkedList<>();
        visit[w.y][w.x] = true;
        q.offer(new Node(w.y, w.x, 0));
        pq.clear();

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 이동 위치 탐색 (물고기 + 빈칸)
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (visit[ny][nx] || map[ny][nx] > w.size) continue;
                visit[ny][nx] = true;
                q.offer(new Node(ny, nx, cur.dist + 1));

                // 먹을 수 있는 물고기이면
                if (1 <= map[ny][nx] && map[ny][nx] < w.size) {
                    pq.offer(new Node(ny, nx, cur.dist + 1));
                }
            }
        }
    }

    static class Whale {
        int y, x, eatCnt, size, sec;
        public Whale() {
            this.size = 2;
        }
        void eat() {
            if (++eatCnt == size) {
                size++;
                eatCnt = 0;
            }
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
