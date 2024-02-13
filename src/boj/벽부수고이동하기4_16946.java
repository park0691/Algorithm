package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 벽부수고이동하기4_16946 {
    static int N, M, groupCnt;
    static List<Integer> count;
    static int[][] map, group;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        group = new int[N][M];
        visit = new boolean[N][M];
        count = new ArrayList<>();
        count.add(0);

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
            }
        }
        // 0인 영역 카운트 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    int cnt = bfs(i, j);
                    count.add(cnt);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Set<Integer> visitGroup = new HashSet<>();
                int cnt = 1;
                if (map[i][j] == 0) {
                    sb.append(0);
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] != 0) continue;

                    int g = group[ny][nx];
                    if (!visitGroup.contains(g)) {
                        cnt += count.get(g);
                        visitGroup.add(g);
                    }
                }

                sb.append(cnt % 10);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static int bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        group[y][x] = ++groupCnt;
        visit[y][x] = true;
        q.offer(new int[] {y, x});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] != 0) continue;
                group[ny][nx] = groupCnt;
                visit[ny][nx] = true;
                q.offer(new int[] {ny, nx});
            }
        }
        return cnt;
    }
}
