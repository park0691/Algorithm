package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7576 {
	static int N, M, disabledCount, tomatoCount, solution;
	static int[][] map;	
	static Queue<int[]> q = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				// 토마토일 때
				if (n == 1) {
					tomatoCount++;
					q.offer(new int[] { i, j, 0 });
				}
				// 토마토가 들어있지 않을 때
				else if (n == -1) disabledCount++;
			}
		}
		
		// 저장될 때부터 모든 토마토가 익어 있는 상태인지 체크
		if (tomatoCount == (N * M - disabledCount)) {
			System.out.println(solution);
			return;
		}
		
		// BFS 시작
		int[] data = null;
		while (!q.isEmpty()) {
			data = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = data[1] + dx[i];
				int ny = data[0] + dy[i];
				
				if (nx < 0 || ny < 0 || ny >= N || nx >= M || map[ny][nx] != 0 ) {
					continue;
				}
				q.offer(new int[] { ny, nx, data[2] + 1 });
				map[ny][nx] = 1;
				tomatoCount++;
			}
		}
		// 토마토가 모두 익지 않았는지 체크
		if (tomatoCount != (N * M - disabledCount)) solution = -1;
		else solution = data[2];

		System.out.println(solution);
	}
}
