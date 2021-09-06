package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회2_10971 {
	static int N, sol;
	static int start;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		boolean[] check = new boolean[N + 1];
		sol = Integer.MAX_VALUE;
		StringTokenizer st;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (start = 1; start <= N; start++)
			dfs(start, 0, 0, map, check);
		
		System.out.println(sol);
	}

	static void dfs(int node, int cost, int len, int[][] map, boolean[] check) {
		// pruning
		// 도착하지 않았는데 시작 노드로 가면 더 이상 진행할 필요 없음 
		if (len > 0 && len < N && node == start) return;
		// 현재 비용이 지금까지 구한 최소 비용보다 크면 진행할 필요 없음
		if (cost > sol) return;
		
		if (len == N) {
			if (node != start) return;
			
			sol = Math.min(sol, cost);
			return;
		}
		
		for (int curNode = 1; curNode <= N; curNode++) {
			int curCost = map[node][curNode];
			if (curCost == 0 || check[curNode]) continue;
			
			check[curNode] = true;
			dfs(curNode, cost + curCost, len + 1, map, check);
			check[curNode] = false;
		}
	}
}
