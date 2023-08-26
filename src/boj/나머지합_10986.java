package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나머지합_10986 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long s[] = new long[N + 1];
        long cnt[] = new long[M];
        long ans = 0;
        int remainder;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            s[i] = s[i - 1] + Long.parseLong(st.nextToken());
            remainder = (int) (s[i] % M);
            if (remainder == 0) ans++;
            cnt[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                ans += (cnt[i] * (cnt[i] - 1) / 2);
            }
        }
        System.out.println(ans);
    }
}
