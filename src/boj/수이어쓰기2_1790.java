package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기2_1790 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long n = 1;
        long digit = 9;
        long nCnt = 9;
        while (true) {
            if (k - nCnt <= 0) break;
            k -= nCnt;
            n++;
            digit *= 10;
            nCnt = n * digit;
        }

        long q = (k - 1) / n;
        long r = (k - 1) % n;
        long result = (long) Math.pow(10, n - 1) + q;
        if (result > N) {
            System.out.println(-1);
        } else {
            long ans = (result / (long) Math.pow(10, n - 1 - r)) % 10;
            System.out.println(ans);
        }
    }
}
