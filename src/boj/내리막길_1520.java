package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길_1520 {

    static int M, N;
    static int[][] map, dp;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        // 현재 칸에서 목적지까지 도달하는 경로의 개수
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int y, int x) {
        if (y == M - 1 && x == N - 1) return 1;
        if (dp[y][x] > -1) return dp[y][x];

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= M || nx < 0 || nx >= N || map[y][x] <= map[ny][nx]) continue;

            dp[y][x] += dfs(ny, nx);
        }

        return dp[y][x];
    }
}