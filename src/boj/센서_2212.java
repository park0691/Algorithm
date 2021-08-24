package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * Greedy
 */
public class 센서_2212 {
	
	static int N, K, sum;
	static int[] pos;
	static Integer[] distance;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		// 집중국의 개수가 N 이상 일 때 각 센서의 위치에 설치하면 되므로 0
		if (K >= N) {
			System.out.println(0);
			System.exit(0);
		}
		
		pos = new int[N];
		sum = 0;
		// 센서 위치 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			pos[i] = Integer.parseInt(st.nextToken());
		// 오름차순 정렬
		Arrays.sort(pos);
		// 센서간 거리 계산 후 내림차순 정렬
		distance = new Integer[N -1];
		for (int i = 1; i < N; i++) {
			int value = pos[i] - pos[i - 1];
			distance[i - 1] = value;
		}
		Arrays.sort(distance, Collections.reverseOrder());
		
		// 가장 긴 거리부터 하나씩 (k - 1) 개 만큼 제거
		for (int i = 0; i < K - 1; i++) {
			distance[i] = 0;
		}
		
		for (int i = 0; i < N - 1; i++) {
			sum += distance[i];
		}
		System.out.println(sum);
	}
}
