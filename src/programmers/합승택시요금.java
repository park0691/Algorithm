package programmers;

import java.util.*;

public class 합승택시요금 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }

        for (int[] f : fares) {
            map[f[0]][f[1]] = f[2];
            map[f[1]][f[0]] = f[2];
        }

        // 거쳐가는 점
        for (int k = 1; k <= n; k++) {
            // 시작점
            for (int i = 1; i <= n; i++) {
                // 도착점
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (map[s][i] == Integer.MAX_VALUE || map[i][b] == Integer.MAX_VALUE || map[i][a] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, map[s][i] + map[i][b] + map[i][a]);
        }

        return answer;
    }
}
