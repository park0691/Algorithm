package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 회장뽑기_2660 {
	static int N;
	static int[][] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];
		
		// adjacency matrix 초기화
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
			graph[i][i] = 0;
		}
		
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == -1) break;
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		// floyd-warshall
		for (int i = 1; i <= N; i++) {
			for (int a = 1; a <= N; a++) {
				for (int b = 1; b <= N; b++) {
					if (graph[a][i] == Integer.MAX_VALUE || graph[i][b] == Integer.MAX_VALUE) continue;
					graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
				}
			}
		}
		
		// 회장 후보의 점수 구하기
		int[] scores = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				max = Math.max(max, graph[i][j]);
			}
			scores[i] = max;
		}
		
		int minScore = scores[1];
		for (int i = 2; i < N; i++) {
			minScore = Math.min(minScore, scores[i]);
		}
		
		// 회장 후보의 수
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (scores[i] == minScore) count++;
		}
		System.out.println(minScore + " " + count);
		
		// 회장 후보를 오름차순 출력
		for (int i = 1; i <= N; i++) {
			if (scores[i] == minScore) System.out.print(i + " ");
		}
	}
}
