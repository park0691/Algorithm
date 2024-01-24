package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 게임2048_12100_2 {

    static int N, ans;
    static int[][] startPos;
    static int[] d = {2, 2, 0, 0};
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        startPos = new int[][] {
                {0, 0},			// 상
                {N - 1, 0},		// 하
                {0, 0},			// 좌
                {0, N - 1}		// 우
        };
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);

        System.out.println(ans);
    }

    static void dfs(int depth, int[][] curBoard) {
        if (depth == 5) return;

        int[][] tmpBoard;

        for (int i = 0; i < 4; i++) {
            tmpBoard = deepCopy(curBoard);
            detect(i, tmpBoard);
            merge(i, tmpBoard);
            dfs(depth + 1, tmpBoard);
        }
    }

    static void merge(int dir, int[][] board) {
        int sy = startPos[dir][0];
        int sx = startPos[dir][1];

        int ny = sy;
        int nx = sx;

        for (int rc = 0; rc < N; rc++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int n = board[ny][nx];

                if (n != 0) list.add(n);
                ans = Math.max(ans, n);
                ny += dy[dir];
                nx += dx[dir];
            }

            ny = sy;
            nx = sx;

            int j = 0;
            while (j < list.size()) {
                board[ny][nx] = list.get(j);
                j++;
                ny += dy[dir];
                nx += dx[dir];
            }
            while (j < N) {
                board[ny][nx] = 0;
                j++;
                ny += dy[dir];
                nx += dx[dir];
            }

            sy += dy[d[dir]];
            sx += dx[d[dir]];
            ny = sy;
            nx = sx;
        }
    }

    static void detect(int dir, int[][] board) {
        Stack<Integer[]> st = new Stack<>();
        int sy = startPos[dir][0];
        int sx = startPos[dir][1];

        int ny = sy, nx = sx;

        for (int yx = 0; yx < N; yx++) {
            st.clear();
            for (int i = 0; i < N; i++) {
                int n = board[ny][nx];

                if (n != 0) {
                    if (st.isEmpty() || st.peek()[0] != n) st.push(new Integer[]{n, ny, nx});
                    // 같은 숫자면, 숫자 합치기
                    else if (st.peek()[0] == n) {
                        // 숫자 합치기
                        int my = st.peek()[1];
                        int mx = st.peek()[2];
                        merge(my, mx, ny, nx, board);
                        st.clear();
                    }
                }

                ny += dy[dir];
                nx += dx[dir];
            }

            // 다음 행 또는 열 단위 이동
            sy += dy[d[dir]];
            sx += dx[d[dir]];
            ny = sy;
            nx = sx;
        }
    }

    static void merge(int my, int mx, int ny, int nx, int[][] board) {
        board[my][mx] *= 2;
        board[ny][nx] = 0;
    }

    static int[][] deepCopy(int[][] curArray) {
        int[][] newArray = new int[N][];
        for (int i = 0; i < N; i++) {
            newArray[i] = Arrays.copyOf(curArray[i], N);
        }
        return newArray;
    }
}
