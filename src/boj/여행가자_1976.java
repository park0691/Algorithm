package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_1976 {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n != 0) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int pa = findParent(a);
        for (int i = 1; i < M; i++) {
            int b = Integer.parseInt(st.nextToken());
            int pb = findParent(b);
            if (pa != pb) {
                System.out.println("NO");
                System.exit(0);
            }
        }

        System.out.println("YES");
    }

    static int findParent(int n) {
        if (parent[n] != n) return parent[n] = findParent(parent[n]);
        else return n;
    }

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }
}
