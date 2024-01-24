package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기_14888 {
    static int N, min, max;
    static int[] arr, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        cnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int res) {
        if (depth == N - 1) {
            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (cnt[i] == 0) continue;
            cnt[i]--;
            switch(i) {
                case 0:
                    dfs(depth + 1, res + arr[depth + 1]);
                    break;
                case 1:
                    dfs(depth + 1, res - arr[depth + 1]);
                    break;
                case 2:
                    dfs(depth + 1, res * arr[depth + 1]);
                    break;
                case 3:
                    dfs(depth + 1, res / arr[depth + 1]);
                    break;
            }
            cnt[i]++;
        }
    }
}
