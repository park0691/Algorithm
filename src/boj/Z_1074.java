package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_1074 {
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, r, c;
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		z(N, r, c);
		System.out.println(count);
	}

	static void z(int n, int r, int c) {
		if (n < 1) {
			return;
		}

		int size = 1 << (2 * (n - 1));
		int base = 1 << (n - 1);

		if (r < base && c < base) {
			z(n - 1, r, c);
		} else if (r < base && c >= base) {
			count += size;
			z(n - 1, r, c - base);
		} else if (r >= base && c < base) {
			count += (2 * size);
			z(n - 1, r - base, c);
		} else {
			count += (3 * size);
			z(n - 1, r - base, c - base);
		}
	}
}
