package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {
	static int N;
	static int switches[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		switches = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if (gender == 1) {
				int j = n;
				while (j <= N) {
					toggle(j);
					j += n;
				}
			} else {
				toggle(n);
				symmetry(n, 1);
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(switches[i] + " ");
			if (i % 20 == 0) System.out.println();
		}
		
		System.out.println();
	}
	
	static void toggle(int index) {
		switches[index] = switches[index] == 0 ? 1 : 0;
	}
	
	static void symmetry(int mid, int i) {
		if (mid - i <= 0 || mid + i > N) return;
		if (switches[mid - i] != switches[mid + i]) return;
		
		toggle(mid - i);
		toggle(mid + i);
		
		symmetry(mid, i + 1);
	}
}
