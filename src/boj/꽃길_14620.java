package boj;

import java.util.Scanner;

public class 꽃길_14620 {

    static int N, ans;
    static boolean[][] visit;
    static int[][] map;
    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, -1, 1, 0, 0};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visit = new boolean[N][N];
        map = new int[N][N];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();

        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int cost, int level) {
        if (level == 3) {
            if (cost < ans) ans = cost;
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (!test(i, j)) continue;
                int curCost = mark(i, j, true);
                dfs(cost + curCost, level + 1);
                mark(i, j, false);
            }
        }
    }

    static boolean test(int y, int x) {
        for (int i = 0; i < 5; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (visit[ny][nx]) return false;
        }

        return true;
    }

    static int mark(int y, int x, boolean val) {
        int cost = 0;

        for (int i = 0; i < 5; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            cost += map[ny][nx];
            visit[ny][nx] = val;
        }

        return cost;
    }
}
