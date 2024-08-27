package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기3_11658 {
    static int N;
    static int[][] arr, tree;

    static void update(int y, int x, int val) {
        while (y <= N) {
            for (int i = x; i <= N; ) {
                tree[y][i] += val;
                i += i & -i;
            }
            y += y & -y;
        }
    }

    static int sum(int y, int x) {
        int sum = 0;
        while (y > 0) {
            for (int i = x; i > 0; ) {
                sum += tree[y][i];
                i -= i & -i;
            }
            y -= y & -y;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        tree = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, arr[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            if (op == 0) {
                int c = Integer.parseInt(st.nextToken());
                update(y1, x1, c - arr[y1][x1]);
                arr[y1][x1] = c;
            } else {
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                sb.append(sum(y2, x2) - sum(y2, x1 - 1) - sum(y1 - 1, x2) + sum(y1 - 1, x1 - 1)).append('\n');
            }
        }
        System.out.println(sb.toString());
    }
}
