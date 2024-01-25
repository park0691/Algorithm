package programmers;

import java.util.*;

public class 석유시추_PCCP기출_2_정사영 {
    static int R, C, ans;
    static boolean[][] visit;
    static int[][] land;
    static int[] amounts;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static int solution(int[][] data) {
        R = data.length;
        C = data[0].length;
        land = data;
        amounts = new int[C];
        visit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (land[i][j] == 0 || visit[i][j]) continue;
                bfs(i, j);
            }
        }

        return ans;
    }

    static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        int count = 1;
        int minX = x, maxX = x;
        visit[y][x] = true;
        q.offer(new int[] {y, x});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ny, nx;
            for (int i = 0; i < 4; i++) {
                ny = cur[0] + dy[i];
                nx = cur[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (land[ny][nx] == 0 || visit[ny][nx]) continue;
                minX = Math.min(minX, nx);
                maxX = Math.max(maxX, nx);
                count++;
                visit[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }

        for (int cx = minX; cx <= maxX; cx++) {
            amounts[cx] += count;
            ans = Math.max(ans, amounts[cx]);
        }
    }

    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] land2 = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        System.out.println(solution(land));
        System.out.println(solution(land2));
    }

}
