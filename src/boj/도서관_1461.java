package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 도서관_1461 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 위치 값이 큰 것 먼저 꺼낼 수 있도록 maxHeap 생성
		Queue<Integer> plus = new PriorityQueue<>((a, b) -> b - a);
		Queue<Integer> minus = new PriorityQueue<>((a, b) -> b - a);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int i = N;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		
		// 책의 위치의 음수/양수 여부에 따라서 다른 큐에 저장
		while (i-- > 0) {
			int n = Integer.parseInt(st.nextToken());
			if (n < 0) {
				minus.offer(-n);
			} else {
				plus.offer(n);
			}
		}
		// 최대 위치 구하기 (책을 모두 제자리에 놔둔 후 다시 0으로 돌아올 필요 X)
		// 나중에 최대 위치에서 종료하여 0으로 되돌아 가지 않도록 한다. 
		int plusMax = -1;
		for (int k : plus) plusMax = Math.max(plusMax, k);
		int minusMax = -1;
		for (int k : minus) minusMax = Math.max(minusMax, k);
		int max = Math.max(plusMax, minusMax);
		
		// 각각의 큐에서 큰 위치부터 M개씩 빼내되 처음 빼내는 값을 이동 횟수에 더한다.
		while (!minus.isEmpty()) {
			int cur = minus.poll();
			ans += cur;
			for (int j = 0; j < M - 1; j++) minus.poll();
		}
		
		while (!plus.isEmpty()) {
			int cur = plus.poll();
			ans += cur;
			for (int j = 0; j < M - 1; j++) plus.poll();
		}
		// 왕복하므로 * 2, 최대 위치에서 종료하므로 뺀다.
		System.out.println(ans * 2 - max);
	}
}
