package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    (A + B) % C = [(A % C) + (B % C)] % C
    S[i] - S[j] : 배열 j + 1 부터 i 까지의 구간합
    S[i] % M == S[j] % M 이면, (S[i] - S[j]) % M = 0
    구간합 배열의 원소를 M 으로 나눈 나머지로 업데이트하고, S[i] == S[j] 인 (i, j) 쌍을 찾으면,
    원본 배열에서 j + 1 부터 i 까지의 구간합이 M으로 나누어 떨어진다.
 */
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
