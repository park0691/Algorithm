package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시피하기_18428_2 {

    static int N, cnt;
    static char[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static boolean ans;
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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S' || map[i][j] == 'T') continue;
                map[i][j] = 'O';
                dfs(0, i, j);
                map[i][j] = 'X';
            }
        }
        System.out.println(ans ? "YES" : "NO");
    }

    static void dfs(int depth, int y, int x) {
        if (ans) return;
        if (depth == 2) {
            solve();
            cnt++;
            return;
        }

        // 다음 위치 좌표로 이동
        if (x == N - 1) {
            y++;
            x = 0;
        } else {
            x++;
        }
        for (int i = y; i < N; i++) {
            if (i > y) {
                x = 0;
            }
            for (int j = x; j < N; j++) {
                if (map[i][j] == 'S' || map[i][j] == 'T') continue;
                map[i][j] = 'O';
                dfs(depth + 1, i, j);
                map[i][j] = 'X';
            }
        }
    }

    static void solve() {
        boolean found = false;
        for (int[] p : teachers) {
            int cy = p[0];
            int cx = p[1];

            for (int y = cy - 1; y >= 0; y--) {
                if (map[y][cx] == 'O') break;
                if (map[y][cx] == 'S') {
                    found = true;
                    break;
                }
            }
            if (found) break;
            for (int y = cy + 1; y < N; y++) {
                if (map[y][cx] == 'O') break;
                if (map[y][cx] == 'S') {
                    found = true;
                    break;
                }
            }
            if (found) break;
            for (int x = cx - 1; x >= 0; x--) {
                if (map[cy][x] == 'O') break;
                if (map[cy][x] == 'S') {
                    found = true;
                    break;
                }
            }
            if (found) break;
            for (int x = cx + 1; x < N; x++) {
                if (map[cy][x] == 'O') break;
                if (map[cy][x] == 'S') {
                    found = true;
                    break;
                }
            }
        }
        ans = !found;
    }
}