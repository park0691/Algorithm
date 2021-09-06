package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수2_11051 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n + 1][n + 1];		
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				if (j == 0 || i == j) dp[i][j] = 1;
				else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
			}
		}
		System.out.println(dp[n][k]);
	}
}
