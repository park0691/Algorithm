package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
/*
N = 4일 때 XXXX인 경우 : 4! = 24가지
	1XXX인 경우 : 3! = 6가지
	
	k	| k - 1	| (k - 1) / 3!
	--------------------------
	1	| 0		| 0
	2	| 1		| 0
	3	| 2		| 0
	4	| 3		| 0
	5	| 6		| 0
	6	| 5		| 0
	7	| 6		| 1
	8	| 7		| 1
	
	2XXX인 경우 : 3! = 6가지
	
	23XX인 경우 : 2! = 2가지
	234X인 경우 : 1! = 1가지

	k == 17 (17번째 순열)
	
	확정되지 않은 숫자 a = { 1, 2, 3, 4 }
	
	(1) 첫 번째 숫자 구하기
		(17 - 1) / 3! = 2
		첫 번째는 3이다.  (a[2] == 3)
		
		k -= 2 * 3!
		-> k = 5
		
	확정되지 않은 숫자 a = { 1, 2, 4 }
	
	(2) 두 번째 숫자 구하기
		(5 - 1) / 2! = 2
		두 번째는 4다. (a[2] == 4)
		
N = 4일 때 1324일 때
	(1, 2, 3, 4) 중 1의 인덱스는 0
	k += 0 * 3!
	
	(2, 3, 4) 중 3의 인덱스는 1
	k += 1 * 2!
	
 */
public class 순열의순서_1722 {
	static int N, M;
	static long k;
	static long[] fact;
	static int[] sol;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		fact = new long[20];					// 19! 까지 필요
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 명령 번호
		
		// factorial 계산
		fact[0] = 1;
		for (int i = 1; i < 20; i++)
			fact[i] = i * fact[i - 1];
		
		// 숫자 리스트 초기화
		List<Integer> numbers = new LinkedList<>();
		for (int i = 1; i <= N; i++) numbers.add(i);
		
		// k 입력받아 k번째 순열 구하기
		if (M == 1) {
			k = Long.parseLong(st.nextToken());
			k--;
			sol = new int[N];
			
			for (int i = 0; i < N; i++) {
				// x : 확정되지 않은 숫자 중 몇 번째
				int x = (int) (k / fact[N - i - 1]);
				sol[i] = numbers.get(x);
				k %= fact[N - i - 1];
				numbers.remove(x);
			}
			for (int n : sol) System.out.print(n + " ");
		} else {
		// 임의의 순열을 나타내는 N개의 수 입력받고, 해당 순열이 몇 번째인지 출력하기
			// 순열 입력
			long cnt = 0;
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				int nIndex = numbers.indexOf(n);
				cnt += nIndex * fact[N - i - 1];
				numbers.remove(nIndex);
			}
			cnt++;
			System.out.println(cnt);
		}
	}
}
