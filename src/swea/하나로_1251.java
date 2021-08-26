package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * MST, Prim Algorithm
 */
public class 하나로_1251 {

	static int T, N;
	static int[][] island;
	static long sum;
	static double E;
	static boolean[] visited;
	static List<List<Edge>> vertices;
	static Queue<Edge> pq = new PriorityQueue<Edge>((e1, e2) -> Long.compare(e1.c, e2.c));
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			island = new int[N][2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) island[i][0] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) island[i][1] = Integer.parseInt(st.nextToken());
			E = Double.parseDouble(br.readLine());
			
			vertices = new ArrayList<>();
			for (int i = 0; i <= N; i++) vertices.add(new ArrayList<>());
			
			for (int v1 = 0; v1 < N - 1; v1++) {
				for (int v2 = 0; v2 < N; v2++) {
					long d = distance(v1, v2);
					Edge e1 = new Edge(v2, d);
					vertices.get(v1).add(e1);
					Edge e2 = new Edge(v1, d);
					vertices.get(v2).add(e2);
				}
			}
			
			visited = new boolean[N];
			pq.clear();
			sum = 0;
			int cnt = 1;
			visited[0] = true;
			pq.addAll(vertices.get(0));
			
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				
				if (visited[e.v]) continue;
				pq.addAll(vertices.get(e.v));
				visited[e.v] = true;
				
				sum += e.c;
				if (cnt++ == N) break;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(Math.round(sum * E)).append("\n");
			bw.write(sb.toString());
		}
		br.close();
		bw.close();
	}
	
	static long distance(int v1, int v2) {
		return (long)(Math.pow(island[v1][0] - island[v2][0], 2) + Math.pow(island[v1][1] - island[v2][1], 2));
	}
	
	static class Edge {
		int v;
		long c;
		
		public Edge(int v, long c) {
			super();
			this.v = v;
			this.c = c;
		}
	}
}
