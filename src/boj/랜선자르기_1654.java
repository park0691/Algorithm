package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 랜선자르기_1654 {
	
	static int[] arr;
	static int K, N;
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());    // 10,000 이하의 정수 (랜선 K개)
		N = Integer.parseInt(st.nextToken());	// 1,000,000 이하의 정수 (필요한 랜선 N개)
		arr = new int[K];

		for (int i = 0; i < K; i++) arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);

		long left = 1, right = arr[K - 1];
		long mid;
		while (left <= right) {
			mid = (left + right) / 2;

			if (test(mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(ans);
	}

	static boolean test(long val) {
		int sum = 0;
		for (int i = 0; i < K; i++) sum += arr[i] / val;

		if (sum >= N) {
			if (ans < val) ans = val;
			return true;
		}

		return false;
	}
}
