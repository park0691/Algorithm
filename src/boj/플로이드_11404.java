package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 플로이드_11404 {
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dp =  new int[N + 1][N + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        for (int i = 1; i <= N; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[a][b] = Math.min(dp[a][b], c);
        }

        for (int v = 1; v <= N; v++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dp[i][v] == Integer.MAX_VALUE || dp[v][j] == Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][v] + dp[v][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) sb.append("0");
                else sb.append(dp[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
