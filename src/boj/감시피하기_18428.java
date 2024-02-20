package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시피하기_18428 {

    static int N;
    static char[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static boolean ans;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') teachers.add(new int[]{i, j});
            }
        }

        dfs(0, 0, 0);
        System.out.println("NO");
    }

    static void dfs(int depth, int y, int x) {
        if (ans) return;
        if (depth == 3) {
            if (solve()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        int j;
        for (int i = y; i < N; i++) {
            if (i > y) j = 0;
            else j = x;
            for ( ; j < N; j++) {
                // 첫 진입 좌표값 'O' PASS
                if (map[i][j] != 'X') continue;
                map[i][j] = 'O';
                dfs(depth + 1, i, j);
                map[i][j] = 'X';
            }
        }
    }

    static boolean solve() {
        for (int[] p : teachers) {
            for (int i = 0; i < 4; i++) {
                int ny = p[0];
                int nx = p[1];

                while (true) {
                    ny += dy[i];
                    nx += dx[i];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) break;
                    if (map[ny][nx] == 'O') break;
                    if (map[ny][nx] == 'S') return false;
                }
            }
        }
        return true;
    }
}