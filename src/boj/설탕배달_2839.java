package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 설탕배달_2839 {
	static int N, min;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
//		풀이 (1) 시간복잡도 높아서 터짐
//		min = Integer.MAX_VALUE;
//		comb(0, 0);
//		System.out.println(min == Integer.MAX_VALUE ? -1 : min);

//		풀이 (2) DP
		if (N <= 5) {
			if (N == 3 || N == 5) System.out.println(1);
			else System.out.println(-1);
			System.exit(0);
		}

		dp = new int[N + 1];
		Arrays.fill(dp, 5000);
		dp[3] = 1;
		dp[5] = 1;

		for (int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}

		if (dp[N] > 5000) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}
	}

	static void comb(int five, int three) {
		int sum = five * 5 + three * 3;
		
		if (sum > N) return;
		
		if (sum == N) {
			min = Math.min(min, five + three);
			return;
		}
		comb(five, three + 1);
		comb(five + 1, three);
	}
}