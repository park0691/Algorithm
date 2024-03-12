package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동_16234 {

    static int N, L, R, groupCnt;
    static int[][] map, group;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] populations = new int[2501];   // 그룹별 연합 인구수
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        group = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[N][N];
        int ans = 0;
        boolean move;

        while (true) {
            // visit init
            for (int i = 0; i < N; i++) Arrays.fill(visit[i], false);
            groupCnt = 0;
            move = false;
            // bfs 탐색으로 그룹핑
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) continue;
                    int v = bfs(i, j);
                    // 인구 이동이 발생할 필요 없는 경우
                    if (v == -1) {
                        continue;
                    }
                    move = true;
                    populations[groupCnt] = v;
                }
            }
            // 인구 이동이 발생하지 않은 경우
            if (!move) break;
            ans++;
            // 그룹별 연합 인구수 셋팅
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (group[i][j] == -1) continue;
                    map[i][j] = populations[group[i][j]];
                }
            }
        }
        System.out.println(ans);
    }

    static int bfs(int y, int x) {
        int sum = map[y][x], cnt = 1;
        groupCnt++;
        group[y][x] = groupCnt;
        visit[y][x] = true;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(y, x));

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (visit[ny][nx]) continue;
                int diff = Math.abs(map[ny][nx] - map[cur.y][cur.x]);
                if (diff < L || diff > R) continue;
                sum += map[ny][nx];
                cnt++;
                group[ny][nx] = groupCnt;
                visit[ny][nx] = true;
                q.offer(new Pos(ny, nx));
            }
        }

        if (cnt == 1) {
            groupCnt--;
            group[y][x] = -1;
            return -1;
        }
        return sum / cnt;
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
