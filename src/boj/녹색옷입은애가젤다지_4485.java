package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지_4485 {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][] cost;
	static Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			map = new int[N][N];
			visit = new boolean[N][N];
			cost = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cost[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			System.out.println("Problem " + t++ + ": " + cost[N - 1][N - 1]);
		}
		
	}

	static void dijkstra() {
		visit[0][0] = true;
		cost[0][0] = map[0][0];
		pq.offer(new Edge(0, 0, cost[0][0]));
		
		int ny, nx, nc;
		Edge e;
		
		while (!pq.isEmpty()) {
			e = pq.poll();

			
			for (int i = 0; i < 4; i++) {
				ny = e.y + dy[i];
				nx = e.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
				
				nc = e.c + map[ny][nx];
				
				if (nc < cost[ny][nx]) {
					visit[ny][nx] = true;
					cost[ny][nx] = nc;
					pq.offer(new Edge(ny, nx, nc));
				}
			}
		}
	}
	
	static class Edge {
		int y, x, c;
		
		public Edge(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}
}
