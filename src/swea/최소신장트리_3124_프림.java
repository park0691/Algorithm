package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 최소신장트리_3124_프림 {
    static int T, V, E;
    static long sum;
    static List<List<Edge>> vertices;
    static boolean[] visit;
    static Queue<Edge> queue = new PriorityQueue<Edge>((e1, e2) -> (e1.c - e2.c));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            sum = 0;
            vertices = new ArrayList<>();
            visit = new boolean[V + 1];
            for (int i = 0; i < V + 1; i++) vertices.add(new ArrayList<>());

            // 간선
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                vertices.get(v1).add(new Edge(v2, c));
                vertices.get(v2).add(new Edge(v1, c));
            }

            queue.clear();

            int cnt = 1;
            visit[1] = true;
            queue.addAll(vertices.get(1));

            while (!queue.isEmpty()) {
                Edge e = queue.poll();
                if (visit[e.v]) continue;

                queue.addAll(vertices.get(e.v));
                visit[e.v] = true;
                sum += e.c;
                cnt++;
                if (cnt == V) break;
            }

            System.out.println("#" + t + " " + sum);
        }
    }

    // 정점에 종속된 간선
    // 연결 대상이 되는 한 개 정점
    static class Edge {
        int v, c;

        public Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }
}