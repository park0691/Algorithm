package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 완전 탐색
public class 치킨배달_15686 {
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[] tgt;
    static int[][] map;
    static List<int[]> shops = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tgt = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    shops.add(new int[]{i, j});
                }
            }
        }

        comb(0, 0);

        System.out.println(ans);

    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            int chickenDist = getChickenDist();
            ans = Math.min(ans, chickenDist);
            return;
        }

        for (int i = srcIdx; i < shops.size(); i++) {
            tgt[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }

    static int getChickenDist() {
        int chickenDist = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] != 1) continue;
                int curDist = Integer.MAX_VALUE;
                // 치킨 거리 구하기
                for (int shopIdx : tgt) {
                    int[] shopPos = shops.get(shopIdx);
                    int sy = shopPos[0];
                    int sx = shopPos[1];
                    int d = Math.abs(sy - y) + Math.abs(sx - x);
                    curDist = Math.min(curDist, d);
                }

                chickenDist += curDist;
            }
        }
        return chickenDist;
    }
}
