package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_14502_2 {
	static int N, M, max;
	static int[][] map, wallMap;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Node> walls = new ArrayList<Node>();		// src
	static int wallSize;
	static int[][] wall = new int[3][2];
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wallMap = new int[N][M];
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) walls.add(new Node(i, j));
			}
		}
		wallSize = walls.size();

		comb(0, 0);

		System.out.println(max);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) {
			virus();
			return;
		}

		if (srcIdx == wallSize) return;

		wall[tgtIdx][0] = walls.get(srcIdx).y;
		wall[tgtIdx][1] = walls.get(srcIdx).x;

		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}

	static void virus() {
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				wallMap[i][j] = map[i][j];
				if (wallMap[i][j] == 2) {
					q.offer(new Node(i, j));
					visit[i][j] = true;
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			wallMap[wall[i][0]][wall[i][1]] = 1;
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int cr = cur.y;
			int cc = cur.x;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc]) continue;

				if (wallMap[nr][nc] == 0) {
					wallMap[nr][nc] = 2;
					q.offer(new Node(nr, nc));
					visit[nr][nc] = true;
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (wallMap[i][j] == 0) sum++;
			}
		}
		max = Math.max(max, sum);
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
