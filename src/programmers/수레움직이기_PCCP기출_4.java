package programmers;

public class 수레움직이기_PCCP기출_4 {
    int R, C, answer;
    int[][] maze;
    int dy[] = { -1, 1, 0, 0 };
    int dx[] = { 0, 0, -1, 1 };
    boolean[][] redVisit, blueVisit;

    public int solution(int[][] maze) {
        R = maze.length;
        C = maze[0].length;
        answer = Integer.MAX_VALUE;
        redVisit = new boolean[R][C];
        blueVisit = new boolean[R][C];
        this.maze = maze;
        int cry = 0, crx = 0, cby = 0, cbx = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                switch (maze[i][j]) {
                    case 1:
                        cry = i; crx = j;
                        break;
                    case 2:
                        cby = i; cbx = j;
                        break;
                }
            }
        }

        redVisit[cry][crx] = true;
        blueVisit[cby][cbx] = true;
        dfs(cry, crx, cby, cbx, 0);

        if (answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }

    void dfs(int ry, int rx, int by, int bx, int level) {

        if (maze[ry][rx] == 3 && maze[by][bx] == 4) {
            answer = Math.min(level, answer);
            return;
        } else if (maze[ry][rx] == 3) {
            for (int b = 0; b < 4; b++) {
                int nby = by + dy[b];
                int nbx = bx + dx[b];

                // 격자 밖 OR 방문했던 칸
                if (nby < 0 || nbx < 0 || nby >= R || nbx >= C || maze[nby][nbx] == 5 || blueVisit[nby][nbx]) continue;
                // 같은 칸
                if (nby == ry && nbx == rx) continue;

                blueVisit[nby][nbx] = true;
                dfs(ry, rx, nby, nbx, level + 1);
                blueVisit[nby][nbx] = false;
            }
            return;
        } else if (maze[by][bx] == 4) {
            for (int r = 0; r < 4; r++) {
                int nry = ry + dy[r];
                int nrx = rx + dx[r];

                // 격자 밖 OR 방문했던 칸
                if (nry < 0 || nrx < 0 || nry >= R || nrx >= C || maze[nry][nrx] == 5 || redVisit[nry][nrx]) continue;
                // 같은 칸
                if (nry == by && nrx == bx) continue;

                redVisit[nry][nrx] = true;
                dfs(nry, nrx, by, bx, level + 1);
                redVisit[nry][nrx] = false;
            }
            return;
        }

        for (int r = 0; r < 4; r++) {
            int nry = ry + dy[r];
            int nrx = rx + dx[r];

            // 격자 밖 OR 방문했던 칸
            if (nry < 0 || nrx < 0 || nry >= R || nrx >= C || maze[nry][nrx] == 5 || redVisit[nry][nrx]) continue;

            redVisit[nry][nrx] = true;

            for (int b = 0; b < 4; b++) {
                int nby = by + dy[b];
                int nbx = bx + dx[b];

                // 격자 밖 OR 방문했던 칸
                if (nby < 0 || nbx < 0 || nby >= R || nbx >= C || maze[nby][nbx] == 5 || blueVisit[nby][nbx]) continue;
                // 같은 칸
                if (nby == nry && nbx == nrx) continue;
                // 자리를 바꾸는 경우
                if (nby == ry && nbx == rx && nry == by && nrx == bx) continue;

                blueVisit[nby][nbx] = true;
                dfs(nry, nrx, nby, nbx, level + 1);
                blueVisit[nby][nbx] = false;
            }

            redVisit[nry][nrx] = false;
        }
    }
}
