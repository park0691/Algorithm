package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 입국심사_3079 {

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

        long left = min;
        long right = max * M;
//        while (left <= right) {
        while (left < right) {
            long mid = left + (right - left) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mid / times[i];
                if (cnt > M) break;
            }

            if (cnt >= M) {
//                right = mid - 1;
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
