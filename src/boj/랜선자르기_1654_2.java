package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 랜선자르기_1654_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] arr = new long[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);

        long left = 1;
        long right = arr[K - 1] + 1;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;

            if (test(arr, mid, N)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    static boolean test(long[] arr, long mid, int N) {
        long cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i] / mid;
        }
        return cnt >= N;
    }
}
