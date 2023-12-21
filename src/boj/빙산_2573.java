package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산_2573 {
    static int[][] map, curMap;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        curMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int level = 0;

        while(true) {
            int waterCnt = 0;
            level++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 바닷물인 경우
                    if (map[i][j] == 0) {
                        waterCnt++;
                        continue;
                    }
                    // 인접한 0의 개수 카운트
                    int adjWaterCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                        if (map[ny][nx] == 0) adjWaterCnt++;
                    }

                    // 빙산 개수 업데이트
                    if (map[i][j] <= adjWaterCnt) {
                        curMap[i][j] = 0;
                        waterCnt++;
                    }
                    else curMap[i][j] = map[i][j] - adjWaterCnt;
                }
            }
            // 빙산이 모두 녹으면
            if (waterCnt == N * M) {
                System.out.println(0);
                break;
            }
            // 배열 복사
            for (int i = 0; i < N; i++) {
                System.arraycopy(curMap[i], 0, map[i], 0, M);
            }
            // 덩어리가 2개 이상
            if (getCount() > 1) {
                System.out.println(level);
                break;
            }
        }
    }

    static int getCount() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        int count = 0;

        for  (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || visit[i][j]) continue;
                q.offer(new int[]{i, j});
                visit[i][j] = true;
                count++;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int ny = cur[0] + dy[k];
                        int nx = cur[1] + dx[k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0 || visit[ny][nx]) continue;
                        q.offer(new int[]{ny, nx});
                        visit[ny][nx] = true;
                    }
                }
            }
        }

        return count;
    }
}
