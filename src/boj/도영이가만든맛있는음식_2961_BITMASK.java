package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식_2961_BITMASK {
    static int N, ans;
    static int[] sin, ssn;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sin = new int[N];
        ssn = new int[N];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sin[i] = Integer.parseInt(st.nextToken());
            ssn[i] = Integer.parseInt(st.nextToken());
        }

        int count = 1 << N;
        for (int i = 1; i < count; i++) {
            int sinSum = 1;
            int ssnSum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sinSum *= sin[j];
                    ssnSum += ssn[j];
                }
            }
            int diff = Math.abs(sinSum - ssnSum);
            if (diff < ans) ans = diff;
        }
        System.out.println(ans);
    }
}
