package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_14502 {
	static int N, M, max;
	static int[][] map, virusMap;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virusMap = new int[N][M];
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wall(0, 0, 0);

		System.out.println(max);
	}

	static void wall(int r, int c, int cnt) {
		if (cnt == 3) {
			virus();
			return;
		}

		if (r == N) {
			return;
		}

		for (int j = c; j < M; j++) {
			if (map[r][j] == 0) {
				map[r][j] = 1;

				int nr = (j + 1 == M ? r + 1 : r);
				int nc = (j + 1) % M;

				wall(nr, nc, cnt + 1);
				map[r][j] = 0;
			}
		}

		for (int i = r + 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;

					int nr = (j + 1 == M ? i + 1 : i);
					int nc = (j + 1) % M;

					wall(nr, nc, cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void virus() {
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				virusMap[i][j] = map[i][j];
				if (virusMap[i][j] == 2) {
					q.offer(new int[]{i, j});
					visit[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || virusMap[nr][nc] == 1) continue;

				virusMap[nr][nc] = 2;
				q.offer(new int[]{nr, nc});
				visit[nr][nc] = true;
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virusMap[i][j] == 0) sum++;
			}
		}
		max = Math.max(max, sum);
	}
}