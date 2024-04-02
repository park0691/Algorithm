package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탐색_2178 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
            }
        }

        int sy = 0; int sx = 0;
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        q.offer(new Pos(sy, sx, 1));
        visit[sy][sx] = true;
        int ans = 0;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if (cur.y == N - 1 && cur.x == M - 1) {
                ans = cur.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 0) continue;

                q.offer(new Pos(ny, nx, cur.cnt + 1));
                visit[ny][nx] = true;
            }
        }

        System.out.println(ans);
    }

    static class Pos {
        int y; int x; int cnt;

        public Pos(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
