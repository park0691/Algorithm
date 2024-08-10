package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_2110 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 1;
        int right = arr[N - 1] - arr[0] + 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (test(arr, mid, C)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    static boolean test(int[] arr, int mid, int C) {
        int cnt = 1;
        int lastLocate = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int locate = arr[i];
            if (locate - lastLocate >= mid) {
                lastLocate = locate;
                cnt++;
            }
        }
        return cnt >= C;
    }
}
