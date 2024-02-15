package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출_13460 {

    static int N, M, ry, rx, by, bx;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static boolean[][][][] visit;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        visit = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    ry = i; rx = j;
                }
                else if (map[i][j] == 'B') {
                    by = i; bx = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        visit[ry][rx][by][bx] = true;
        q.offer(new Node(ry, rx, by, bx, 0));
        int nry, nrx, nby, nbx;
        int ans = -1;

        while (!q.isEmpty()) {
            // 다음 번 이동할 위치 꺼내기
            Node cur = q.poll();
            // 10번 초과시
            if (cur.count > 10) break;
            // 10번 이하, 빨간 공 통과 && 파란 공 통과 X
            if (map[cur.ry][cur.rx] == 'O' && map[cur.by][cur.bx] != 'O') {
                ans = cur.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                // 현재 위치로 초기화
                nry = cur.ry;
                nrx = cur.rx;
                nby = cur.by;
                nbx = cur.bx;

                // 빨간 공 기울기
                while (true) {
                    // 다음 번 이동할 위치의 벽, 구멍 체크
                    if (map[nry][nrx] != '#' && map[nry][nrx] != 'O') {
                        // 벽, 구멍 없으면 다음 위치로 이동
                        // --> while(true) : 벽, 구멍까지 쭉 굴린다
                        nry += dy[i]; nrx += dx[i];
                    } else {
                        // 공이 이동한 위치가 벽이면, 이전 위치로
                        if (map[nry][nrx] == '#') {
                            nry -= dy[i]; nrx -= dx[i];
                        }
                        break;
                    }
                }

                // 파란 공 기울기
                while (true) {
                    // 다음 번 이동할 위치의 벽, 구멍 체크
                    if (map[nby][nbx] != '#' && map[nby][nbx] != 'O') {
                        // 벽, 구멍 없으면 다음 위치로 이동
                        // --> while(true) : 벽, 구멍까지 쭉 굴린다
                        nby += dy[i]; nbx += dx[i];
                    } else {
                        // 공이 이동한 위치가 벽이면, 이전 위치로
                        if (map[nby][nbx] == '#') {
                            nby -= dy[i]; nbx -= dx[i];
                        }
                        break;
                    }
                }

                // 빨간 공, 파란공 같은 곳 도착
                if (nry == nby && nrx == nbx) {
                    // 구멍 아닌 경우
                    if (map[nry][nrx] != 'O') {
                        // 절대 이동 거리 계산
                        int redDist = Math.abs(nry - cur.ry) + Math.abs(nrx - cur.rx);
                        int blueDist = Math.abs(nby - cur.by) + Math.abs(nbx - cur.bx);
                        // 파란색이 먼저 구멍 통과한 경우(파란공 거리 짧을 때), 빨간 공 이전 위치로
                        if (redDist > blueDist) {
                            nry -= dy[i]; nrx -= dx[i];
                        } else {
                            nby -= dy[i]; nbx -= dx[i];
                        }
                    }
                }

                if (!visit[nry][nrx][nby][nbx]) {
                    visit[nry][nrx][nby][nbx] = true;
                    q.offer(new Node(nry, nrx, nby, nbx, cur.count + 1));
                }
            }
        }
        return ans;
    }

    static class Node {
        // 빨간 공, 파란 공의 위치 동시에 기억해야 함
        int ry, rx, by, bx, count;

        public Node(int ry, int rx, int by, int bx, int count) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.count = count;
        }
    }
}
