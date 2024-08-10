package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 입국심사_3079_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] times = new int[N];
        long max = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, times[i]);
            min = Math.min(min, times[i]);
        }

        long left = min - 1;
        long right = max * M + 1;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;

            if (test(mid, times, M)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(right);
    }

    static boolean test(long mid, int[] times, int M) {
        long cnt = 0;

        for (int i = 0; i < times.length; i++) {
            cnt += mid / times[i];
            if (cnt > M) break;
        }

        return cnt >= M;
    }
}
