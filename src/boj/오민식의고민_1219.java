package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 오민식의고민_1219 {
    static int SRC, DST, V, E;
    static long[] dist;
    static int[] profits;
    static List<Pair>[] map;
    static boolean[] visit;
    static Queue<Integer> cycleNode = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        SRC = Integer.parseInt(st.nextToken());
        DST = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            map[i] = new ArrayList<>();
        }
        visit = new boolean[V];
        dist = new long[V];
        profits = new int[V];
        Arrays.fill(dist, Long.MAX_VALUE);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            map[from].add(new Pair(to, price));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            profits[i] = Integer.parseInt(st.nextToken());
        }

        dist[SRC] = -profits[SRC];

        // V 번
        for (int n = 1; n <= V; n++) {
            for (int v = 0; v < V; v++) {
                for (Pair t : map[v]) {
                    int dst = t.dst;
                    int price = t.price;
                    if (dist[v] != Long.MAX_VALUE && dist[v] + price - profits[dst] < dist[dst]) {
                        dist[dst] = dist[v] + price - profits[dst];
                        // cycle node 인 경우
                        if (n == V) {
                            visit[v] = true;
                            cycleNode.offer(v);
                        }
                    }
                }
            }
        }
        // 도착할 수 없는 경우 (연결 X)
        if (dist[DST] == Long.MAX_VALUE) {
            System.out.println("gg");
        } else {
            if (bfs()) System.out.println("Gee");
            else System.out.println(-dist[DST]);
        }
    }

    // cycle (node)서 도착점으로 경로 존재 여부 판단
    // 도착점으로 경로 존재하면 -> 돈을 무한히 가질 수 있음
    // (단순히 음수 사이클이 존재한다고 해서 도착점까지 가는데 음수 사이클의 존재를 보장할 수 없으므로 체크함)
    static boolean bfs() {
        while (!cycleNode.isEmpty()) {
            int cur = cycleNode.poll();

            for (Pair p : map[cur]) {
                int next = p.dst;
                if (visit[next]) continue;
                visit[next] = true;
                cycleNode.offer(next);
            }
        }

        return visit[DST];
    }

    static class Pair {
        int dst, price;
        Pair(int dst, int price) {
            this.dst = dst;
            this.price = price;
        }
    }
}
