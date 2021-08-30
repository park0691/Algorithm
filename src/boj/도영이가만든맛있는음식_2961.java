package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 완전 탐색 - 조합
 */
public class 도영이가만든맛있는음식_2961 {
	static int N, ans;
	static int[] s, b;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tgt = new int[N];
		s = new int[N];
		b = new int[N];
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0, 1, 0);
		System.out.println(ans);
	}
	/*
	 * TODO : tgt 배열 없이 구현해보기
	 */
	static void comb(int tgtIdx, int srcIdx, int sSum, int bSum) {
		if (tgtIdx >= 1 && tgtIdx <= N) {
			int diff = Math.abs(sSum - bSum);
			ans = Math.min(ans, diff);
		}
		
		if (tgtIdx == N || srcIdx == N) return;
		
		tgt[tgtIdx] = srcIdx;
		comb(tgtIdx + 1, srcIdx + 1, sSum * s[srcIdx], bSum + b[srcIdx]);
		comb(tgtIdx, srcIdx + 1, sSum, bSum);
	}
}
