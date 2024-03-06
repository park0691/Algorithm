package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 다이어트_19942 {

    static int N, minCost;
    static int[] min = new int[4];
    static int[][] ingredients;
    static Set<Integer> combinations = new TreeSet<>();
    static String minString;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) min[i] = Integer.parseInt(st.nextToken());

        minCost = Integer.MAX_VALUE;
        ingredients = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        if (minCost == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }

        System.out.println(minCost);
        System.out.println(minString);
    }

    static void dfs(int level) {
        check();

        for (int i = level; i < N; i++) {
            combinations.add(i);
            dfs(i + 1);
            combinations.remove(i);
        }
    }

    static void check() {
        // set iterator 순회
        Iterator<Integer> it = combinations.iterator();
        int cp = 0, cf = 0, cs = 0, cv = 0, cc = 0;
        while (it.hasNext()) {
            Integer idx = it.next();
            cp += ingredients[idx][0];
            cf += ingredients[idx][1];
            cs += ingredients[idx][2];
            cv += ingredients[idx][3];
            cc += ingredients[idx][4];
        }

        if (cp < min[0] || cf < min[1] || cs < min[2] || cv < min[3]) {
            return;
        }

        if (cc >= minCost) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer n : combinations) {
            sb.append(n + 1).append(' ');
        }
        minCost = cc;
        minString = sb.toString();
    }
}
