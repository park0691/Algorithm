package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각_14391 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int map[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int c = 0;
        int cnt = 1 << (n * m);
        int ans = 0;
        while (true) {
            int sum = 0;
            if (c >= cnt) {
                break;
            }
            // 가로 0 찾기
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    int k = i * m + j;
                    if ((c & (1 << k)) == 0) {
                        cur *= 10;
                        cur += map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            for (int j = 0; j < m; j++) {
                int cur = 0;
                for (int i = 0; i < n; i++) {
                    int k = i * m + j;
                    if ((c & (1 << k)) != 0) {
                        cur *= 10;
                        cur += map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            ans = Math.max(ans, sum);
            c++;
        }

        System.out.println(ans);
    }
}
