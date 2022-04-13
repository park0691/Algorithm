package boj;

import java.util.Scanner;

public class 나무자르기_2805 {

	static int N, M, ans;
	static int[] arr;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 나무의 수
		M = sc.nextInt();	// 가져갈 나무의 길이
		arr = new int[N];
		int left = 0, right = -1;
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			right = Math.max(right, arr[i]);
		}
		
		int mid;
		
		while (left <= right) {
			mid = (left + right) / 2;
		
			if (test(mid)) left = mid + 1;	// 값 늘려
			else right = mid - 1;			// 값 줄여
		}
		System.out.println(ans);
	}
	
	static boolean test(int height) {
		// 각 나무의 높이 <= 1,000,000,000
		long sum = 0;
		
		for (int n : arr) {
			if (n > height) sum += (n - height);
		}
		
		if (sum >= M) {
			ans = Math.max(ans, height);
			return true;
		}
		else return false;
	}
}
