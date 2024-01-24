package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게임2048_12100 {

    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                ans = Math.max(board[i][j], ans);
            }
        }

        dfs(0, board);

        System.out.println(ans);
    }

    static void dfs(int depth, int[][] curBoard) {
        if (depth == 5) return;

        int[][] tmpBoard = deepCopy(curBoard);
        int[][] board = new int[N][N];
        for (int i = 0; i < 4; i++) {
            copy(board, tmpBoard);
            move(i, board);
            sum(i, board);
            move(i, board);
            dfs(depth + 1, board);
        }
    }

    static void sum(int dir, int[][] board) {
        switch (dir) {
            case 0:
                for (int j = 0; j < N; j++) {
                    int p = 0;
                    while (true) {
                        if (p >= N - 1 || board[p][j] == 0) break;
                        if (board[p][j] == board[p + 1][j]) {
                            board[p][j] = 2 * board[p][j];
                            board[p + 1][j] = 0;
                            ans = Math.max(board[p][j], ans);
                            p++;
                        }
                        p++;
                    }
                }
                break;
            case 1:
                for (int j = 0; j < N; j++) {
                    int p = N - 1;
                    while (true) {
                        if (p <= 0 || board[p][j] == 0) break;
                        if (board[p][j] == board[p - 1][j]) {
                            board[p][j] = 2 * board[p][j];
                            board[p - 1][j] = 0;
                            ans = Math.max(board[p][j], ans);
                            p--;
                        }
                        p--;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {
                    int p = 0;
                    while (true) {
                        if (p >= N - 1 || board[i][p] == 0) break;
                        if (board[i][p] == board[i][p + 1]) {
                            board[i][p] = 2 * board[i][p];
                            board[i][p + 1] = 0;
                            ans = Math.max(board[i][p], ans);
                            p++;
                        }
                        p++;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < N; i++) {
                    int p = N - 1;
                    while (true) {
                        if (p <= 0 || board[i][p] == 0) break;
                        if (board[i][p] == board[i][p - 1]) {
                            board[i][p] = 2 * board[i][p];
                            board[i][p - 1] = 0;
                            ans = Math.max(board[i][p], ans);
                            p--;
                        }
                        p--;
                    }
                }
                break;
        }
    }

    static void move(int dir, int[][] board) {
        switch(dir) {
            case 0:
                for (int j = 0; j < N; j++) {
                    int baseIdx = -1, nIdx = -1;
                    // base 위, n 아래 가정
                    while (true) {
                        // 베이스 인덱스 0인 곳 찾기
                        baseIdx++;
                        while (baseIdx < N && board[baseIdx][j] != 0) baseIdx++;

                        // 숫자 찾기
                        nIdx++;
                        while (nIdx < N && board[nIdx][j] == 0) nIdx++;

                        if (baseIdx >= N || nIdx >= N) break;
                        if (nIdx <= baseIdx) {
                            nIdx = baseIdx;
                            baseIdx--;
                            continue;
                        }
//                        if (nIdx < baseIdx) {
//                            nIdx = baseIdx + 1;
//                            while (nIdx < N && board[nIdx][j] == 0) nIdx++;
//                            if (nIdx == N) break;
//                        }

                        board[baseIdx][j] = board[nIdx][j];
                        board[nIdx][j] = 0;
                    }
                }
                break;
            case 1:
                for (int j = 0; j < N; j++) {
                    int baseIdx = N, nIdx = N;
                    // base 아래, n 위 가정
                    while (true) {
                        // 베이스 인덱스 0인 곳 찾기
                        baseIdx--;
                        while (baseIdx >= 0 && board[baseIdx][j] != 0) baseIdx--;

                        // 숫자 찾기
                        nIdx--;
                        while (nIdx >= 0 && board[nIdx][j] == 0) nIdx--;

                        if (baseIdx <= -1 || nIdx <= -1) break;
                        if (nIdx >= baseIdx) {
                            nIdx = baseIdx;
                            baseIdx++;
                            continue;
                        }
//                        if (nIdx > baseIdx) {
//                            nIdx = baseIdx - 1;
//                            while (nIdx >= 0 && board[nIdx][j] == 0) nIdx--;
//                            if (nIdx == -1) break;
//                        }

                        board[baseIdx][j] = board[nIdx][j];
                        board[nIdx][j] = 0;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {
                    int baseIdx = -1, nIdx = -1;
                    // base 좌, n 우 가정
                    while (true) {
                        // 베이스 인덱스 0인 곳 찾기
                        baseIdx++;
                        while (baseIdx < N && board[i][baseIdx] != 0) baseIdx++;

                        // 숫자 찾기
                        nIdx++;
                        while (nIdx < N && board[i][nIdx] == 0) nIdx++;

                        if (baseIdx >= N || nIdx >= N) break;
                        if (nIdx <= baseIdx) {
                            nIdx = baseIdx;
                            baseIdx--;
                            continue;
                        }
//                        if (nIdx < baseIdx) {
//                            nIdx = baseIdx + 1;
//                            while (nIdx < N && board[i][nIdx] == 0) nIdx++;
//                            if (nIdx == N) break;
//                        }

                        board[i][baseIdx] = board[i][nIdx];
                        board[i][nIdx] = 0;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < N; i++) {
                    int baseIdx = N, nIdx = N;
                    // base 우, n 좌 가정
                    while (true) {
                        // 베이스 인덱스 0인 곳 찾기
                        baseIdx--;
                        while (baseIdx >= 0 && board[i][baseIdx] != 0) baseIdx--;

                        // 숫자 찾기
                        nIdx--;
                        while (nIdx >= 0 && board[i][nIdx] == 0) nIdx--;

                        if (baseIdx <= -1 || nIdx <= -1) break;
                        if (nIdx >= baseIdx) {
                            nIdx = baseIdx;
                            baseIdx++;
                            continue;
                        }
//                        if (nIdx > baseIdx) {
//                            nIdx = baseIdx - 1;
//                            while (nIdx >= 0 && board[i][nIdx] == 0) nIdx--;
//                            if (nIdx == -1) break;
//                        }

                        board[i][baseIdx] = board[i][nIdx];
                        board[i][nIdx] = 0;
                    }
                }
                break;
        }
    }

    static int[][] deepCopy(int[][] curArray) {
        int[][] newArray = new int[N][];
        for (int i = 0; i < N; i++) {
            newArray[i] = Arrays.copyOf(curArray[i], N);
        }
        return newArray;
    }

    static void copy(int[][] targetArray, int[][] srcArray) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                targetArray[i][j] = srcArray[i][j];
            }
        }
    }
}
