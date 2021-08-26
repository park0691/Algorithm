package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Dijkstra Algorithm Practice
 */
public class 최단경로_1753 {
	
	static int V, E, K;
	static int[] distances;
	static List<List<Integer[]>> graph = new ArrayList<>();
	static Queue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		// 그래프 초기화
		for (int i = 0; i < V + 1; i++) graph.add(new ArrayList<>());
		// 거리 배열 초기화
		distances = new int[V + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Integer[] { to, cost });
		}
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) sb.append(distances[i] == Integer.MAX_VALUE ? "INF" : distances[i]).append("\n");
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static void dijkstra() {
		pq.offer(new Integer[] { K, 0 });
		distances[K] = 0;
		
		while (!pq.isEmpty()) {
			Integer[] temp = pq.poll();
			int cv = temp[0];
			int d = temp[1];
			
			// 현재 정점의 d값이 크다면 이미 방문한 정점 (최소 코스트 계산 완료)
			if (distances[cv] < d) continue;
			
			// 현재 정점과 인접한 모든 정점 탐색
			for (Integer[] datas : graph.get(cv)) {
				int nv = datas[0];
				int nd = datas[1];
				int newDistance = distances[cv] + nd;
				if (newDistance < distances[nv]) {
					pq.offer(new Integer[] { nv, newDistance });
					distances[nv] = newDistance;
				}
			}
		}
	}
}
