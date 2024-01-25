package programmers;

import java.util.*;

public class 석유시추_PCCP기출_2_클래스 {
    static int R, C, count;
    static boolean[][] visit;
    static int[][] land;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static int solution(int[][] data) {
        R = data.length;
        C = data[0].length;
        land = data;
        visit = new boolean[R][C];
        List<Oil> oils = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (land[i][j] == 0 || visit[i][j]) continue;
                oils.add(bfs(i, j));
            }
        }

        int ans = 0;
        int[] amounts = new int[C];

        for (Oil o : oils) {
            for (int y : o.xPosSet) {
                amounts[y] += o.count;
                ans = Math.max(ans, amounts[y]);
            }
        }

        return ans;
    }

    static Oil bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> xPos = new HashSet<>();
        int count = 1;
        visit[y][x] = true;
        xPos.add(x);
        q.offer(new int[] {y, x});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ny, nx;
            for (int i = 0; i < 4; i++) {
                ny = cur[0] + dy[i];
                nx = cur[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (land[ny][nx] == 0 || visit[ny][nx]) continue;
                count++;
                visit[ny][nx] = true;
                xPos.add(nx);
                q.offer(new int[]{ny, nx});
            }
        }

        return new Oil(count, xPos);
    }

    static void dfs(int y, int x, Set<Integer> set) {
        int ny, nx;
        count++;

        for (int i = 0; i < 4; i++) {
            ny = y + dy[i];
            nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
            if (land[ny][nx] == 0 || visit[ny][nx]) continue;
            visit[ny][nx] = true;
            set.add(nx);
            dfs(ny, nx, set);
        }
    }

    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] land2 = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        System.out.println(solution(land));
        System.out.println(solution(land2));
    }

    static class Oil {
        public int count;
        public Set<Integer> xPosSet;

        public Oil(int count, Set<Integer> xPosSet) {
            this.count = count;
            this.xPosSet = xPosSet;
        }
    }
}
